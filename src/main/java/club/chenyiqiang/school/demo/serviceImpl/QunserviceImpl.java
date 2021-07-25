package club.chenyiqiang.school.demo.serviceImpl;

import club.chenyiqiang.school.demo.bean.*;
import club.chenyiqiang.school.demo.dao.QunDao;
import club.chenyiqiang.school.demo.dao.UserDao;
import club.chenyiqiang.school.demo.service.QunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QunserviceImpl implements QunService {
    @Autowired
    private QunDao dao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result isInQun(String acc, long qNum) {
        int code=dao.isInQun(acc,qNum);
        return code>0?Result.setSuccess("在群里"):Result.setError("不在群里");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Result addQun(String acc,String oppAcc,long qNum) {
        if(dao.isHasQun(qNum)==0) {
            return Result.setError("失败:群不存在");
        }
        int code=isInQun(acc,qNum).getCode();
        if(code==1) {
            return Result.setError("账号已在群里");
        }
        String name=userDao.getAccName(acc);
        int lastNum=dao.recNewsNum(qNum);
        int role=2;//普通用户
        dao.addQun1(acc,name,qNum,lastNum,role);
        String var_acc=acc+",";
        dao.addQun2(qNum,var_acc);
        dao.addQun3(qNum+",",acc);

        return dao.addNews(qNum,acc,oppAcc,"加群",0,0,9,new Date().getTime())>0?Result.setSuccess("加群成功"):Result.setError("加群失败");
    }
    @Override
    public Result dealAddQun(String acc,String oppAcc,long qNum,int id,int doing)
    {
        Result re;
        if(doing==1){
            re=addQun(acc,oppAcc,qNum);
            dao.updateAdd(qNum, oppAcc, 1, id);
        }else {
            dao.updateAdd(qNum, oppAcc, -3, id);
            return Result.setSuccess("无视加群");
        }
        return re;
    }

    @Override
    public Result sendByUser(String acc,String str, long qNum,long time) {
        if(dao.isHasQun(qNum)==0) {
            return Result.setError("失败:群不存在");
        }
        if(dao.isInQun(acc,qNum)==0){
            return Result.setError("失败:用户无权限");
        }
        return dao.addNews(qNum,acc,null,str,0,0,1,time)>0?Result.setSuccess("消息发生成功"):Result.setError("消息发生失败");
    }

    @Override
    public Result sendFileByUser(String acc, long qNum, String str,int num,long time) {

        return dao.addNews(qNum,acc,"root",str,0,num,5,time)>0?Result.setSuccess("成功"):Result.setError("失败");
    }

    @Override
    public Result sendBySys(String acc,String oppAcc,String str, int type,long qNum,long time) {

        return dao.addNews(qNum,acc,oppAcc,str,0,0,type,time)>0?Result.setSuccess("消息发生成功"):Result.setError("消息发生失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Result create(String acc,String name,String img,long qNum,String type) {
        int code=dao.isHasQun(qNum);
        if(code>0){
            return Result.setError("已经存在群号");
        }
        dao.insertQun(qNum,name,img,acc,new Date().getTime(),type);
        dao.createQun(qNum);
        dao.createQunJl(qNum);
        addQun(acc,"root",qNum);
        return Result.setSuccess("成功");
    }

    @Override
    public Result recNews(String acc, long qNum) {
        if(dao.isHasQun(qNum)==0) {
            return Result.setError("失败:群不存在");
        }
        if(dao.isInQun(acc,qNum)==0){
            return Result.setError("失败:用户无权限");
        }
        int lastNum=dao.recLasNews(acc,qNum);
       // System.out.println("获取消息:"+lastNum);
        List<QunNews> news=dao.recNews(qNum,lastNum);
       for (int i=0;i<news.size();i++){
           Date time=new Date(Long.valueOf(news.get(i).getTime()));
           SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           news.get(i).setSimTime(news.get(i).getTime());
           news.get(i).setTime(format.format(time));
           news.get(i).setSelf(news.get(i).getAcc().equals(acc)?1:0);
           news.get(i).setAcc_name(dao.getAccName(news.get(i).getAcc()));
       //    System.out.println(news.get(i).getAcc_name());
       }
        int num=dao.recNewsNum(qNum);
        return dao.recNewsSuccess(acc,qNum,num)>0?Result.setSuccess(news.toString()):Result.setError("失败");
    }

    @Override
    public Result findByqNum(long qNum) {
        Qun qun=dao.findQun(qNum);
        if(qun==null){
            return  Result.setError("不存在");
        }
        return Result.setSuccess(qun.toString());
    }

    @Override
    public Result isHasQun(long qNum) {
        return dao.isHasQun(qNum)>0?Result.setSuccess("存在"):Result.setError("不存在");
    }

    @Override
    public Result shenqing(String acc, long qNum) {
        String name=dao.getAccName(acc);
        int code=dao.isInQun(acc,qNum);
        if(code==1){
            System.out.println("已经在群");
            return Result.setError("已经在群");
        }
        System.out.println("acc:"+acc+"正在申请加群");
        return sendBySys(acc,null,"申请加群",-2,qNum,new Date().getTime());
    }

    @Override
    public Result dealSq(String acc) {

        List<Long> qNum=dao.getQun(acc);
        List<QunNews> list=new ArrayList<>();
        for (int i=0;i<qNum.size();i++){
            long q=qNum.get(i);
           // System.out.println(q);
            QunNews dealq=dao.getSqAcc(q);
            if(dealq==null){
                continue;
            }
            Date time=new Date(Long.valueOf(dealq.getTime()));
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dealq.setTime(format.format(time));
            dealq.setqNum(q);
            dealq.setAcc_name(dao.getAccName(dealq.getAcc()));
            list.add(dealq);
        }
       // System.out.println("返回的信息"+list.toString());
        return Result.setSuccess(list.toString());
    }

    @Override
    public Result init_getqun(String acc) {
        String qs=userDao.getQunByAcc(acc);
        String[] quns=qs.split(",");
        List<Qun> list=new ArrayList<>();
        for (int i=0;i< quns.length;i++){
            String str=quns[i].replace(",","").trim();
            if(str==null||str.length()==0){
                continue;
            }
            System.out.println("字符："+str);
            list.add(dao.findQun(Long.valueOf(str)));
        }
        System.out.println("用户:"+acc+",获取的群信息是:"+list.toString());
        return Result.setSuccess(list.toString());
    }

    @Override
    public Result getQunUser(long qNum) {
        return Result.setSuccess(dao.getQunUser(qNum).toString());
    }

    @Override
    public Result updateNotice(String notice, String acc, long qNum) {
        int re=dao.queryQuanxian(acc,qNum);
        if(re!=1){
            return Result.setError("没有权限");
        }
        return dao.updateNotice(notice,qNum)>0?Result.setSuccess("成功"):Result.setError("失败");
    }

    @Override
    public Result getHis(long qNum) {
        List<QunNews> list=dao.getHis(qNum);
        for (int i=0;i<list.size();i++){
            QunNews news=list.get(i);
            // System.out.println(q);
            Date time=new Date(Long.valueOf(news.getTime()));
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            news.setTime(format.format(time));
            news.setAcc_name(dao.getAccName(news.getAcc()));
        }
        return Result.setSuccess(list.toString());
    }
}
