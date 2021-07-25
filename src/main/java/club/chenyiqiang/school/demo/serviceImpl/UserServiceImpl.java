package club.chenyiqiang.school.demo.serviceImpl;

import club.chenyiqiang.school.demo.bean.Qun;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.bean.UserInfo;
import club.chenyiqiang.school.demo.dao.QunDao;
import club.chenyiqiang.school.demo.dao.UserDao;
import club.chenyiqiang.school.demo.service.UserService;
import club.chenyiqiang.school.demo.utils.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private QunDao qunDao;
    @Override
    public Result check(String acc, String pass) {

        if(userDao.isHasAcc(acc)==0){
            return Result.setError("账号不存在");
        }
        if(userDao.isStat(acc)!=0){
            return Result.setError("账号非正常状态");
        }
        return userDao.check(acc,pass)>0?Result.setSuccess("登录成功"):Result.setError("密码错误");
    }


    @Override
    public Result checkRoot(String acc, String pass) {
        if(userDao.isHasAcc(acc)==0){
            return Result.setError("账号不存在");
        }
        if(userDao.isStat(acc)!=0){
            return Result.setError("账号非正常状态");
        }
        return userDao.checkRoot(acc,pass)>0?Result.setSuccess("登录成功"):Result.setError("密码错误");

    }

    @Override
    public Result send_yzm(String acc,String email) {
        if(userDao.checkEmail(acc,email)==0){
            return Result.setError("发送失败：账户绑定邮箱信息不准确");
        }
        Redis.add_yzm(acc,email);
        return Result.setSuccess("发送成功");
    }

    @Override
    public Result update_pass(String acc,String pass,String email,String yzm) {
        if(userDao.checkEmail(acc,email)==0){
            return Result.setError("验证失败：账户绑定邮箱信息不准确");
        }
        if(!Redis.yz_yzm(acc,yzm)){
            return Result.setError("验证码错误");
        }
        int code=userDao.updatePass(acc,pass);

        return  userDao.updatePass(acc,pass)>0?Result.setSuccess("修改成功"):Result.setError("修改失败");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Result logon(User user) {
        userDao.initAcc1(user);
        userDao.initAcc2(user);
        String acc=user.getAccount();
        String name=userDao.getAccName(acc);
        long qNum=123456789;
        int lastNum=qunDao.recNewsNum(qNum);
        int role=2;//普通用户
        qunDao.addQun1(acc,name,qNum,lastNum,role);
        String var_acc=","+acc;
        qunDao.addQun2(qNum,var_acc);
        return qunDao.addNews(qNum,acc,"root","加群",0,0,9,new Date().getTime())>0?Result.setSuccess("注册成功"):Result.setError("注册失败");

    }

    @Override
    public User getUser(String acc) {
        return userDao.getUser(acc);
    }

    @Override
    public Result initLogin(String acc, String ip, long time) {
        ip=ip+",";
        String t=time+",";
        return userDao.initlogin(acc,ip,t)>0?Result.setSuccess("成功"):Result.setError("失败");
    }

    @Override
    public Result init_24() {
        return null;
    }
}