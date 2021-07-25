package club.chenyiqiang.school.demo.serviceImpl;

import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.dao.FriendDao;
import club.chenyiqiang.school.demo.dao.UserDao;
import club.chenyiqiang.school.demo.service.FriendmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class FriendmsgServiceImpl implements FriendmsgService {

    @Autowired
    private FriendDao dao;
    @Autowired
    private UserDao userDao;
    @Override
    public Result getHuifu() {
        List<FriendsNews> huifu=dao.getHuifu();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i=0;i<huifu.size();i++){
            huifu.get(i).setTime(format.format(new Date(Long.valueOf(huifu.get(i).getTime()))));
            huifu.get(i).setName(userDao.getAccName(huifu.get(i).getAcc()));
        }
        return Result.setSuccess(huifu.toString());
    }

    @Override
    public Result addHuifu(FriendsNews news) {
        news.setTime(new Date().getTime()+"");
        news.setZan(0);
        news.setPlNum(0);
        Result result=dao.addHuifu(news)>0?Result.setSuccess("添加成功"):Result.setError("添加失败");
        if(result.getCode()==1){
            dao.updatePlNum(news.getOppid());
        }
        return result;
    }

    @Override
    public Result getPinglun(int id,int lever) {
        List<FriendsNews> huifu=dao.getPingLun(id,lever);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (int i=0;i<huifu.size();i++){
            huifu.get(i).setTime(format.format(new Date(Long.valueOf(huifu.get(i).getTime()))));
            huifu.get(i).setName(userDao.getAccName(huifu.get(i).getAcc()));
        }
        return Result.setSuccess(huifu.toString());
    }

    @Override
    public Result delefriendmsg(int id) {

        return dao.deleteByid(id)>0?Result.setSuccess("删除成功"):Result.setError("删除失败");
    }

    @Override
    public Result addZan(int id) {
        return dao.updateZanNum(id)>0?Result.setSuccess("添加成功"):Result.setError("添加失败");
    }
}
