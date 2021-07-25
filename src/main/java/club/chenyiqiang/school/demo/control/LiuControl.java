package club.chenyiqiang.school.demo.control;


import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.LiuYan;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.service.LiuyanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LiuControl {
    @Autowired
    private LiuyanService service;

    @RequestMapping(value = "/json/liuyanmsg")
    @ResponseBody
    Result liuyanmsg(){
        return service.getHuifu();
    }
    ///json/friendmsgByoppid
    ///delete/deleteLiuyan
    @RequestMapping(value = "/delete/deleteLiuyan")
    @ResponseBody
    Result deleteLiuyan(int id){

        return service.deleteLy(id);
    }
    @RequestMapping(value = "/json/liuyanmsgByoppid")
    @ResponseBody
    Result liuyanByid(int id,int level){
        System.out.println("当前的层数是:"+level);
        level=level+1;
        System.out.println("返回的层数是:"+level);
        return service.getPinglun(id,level);
    }

    @RequestMapping(value = "/add/liuyanmsgZanByid")
    @ResponseBody
    Result liuyanmsgZanByid(int id){
        System.out.println("当前id是:"+id);
        return service.addZan(id);
    }
    // method = {RequestMethod.POST}
    @RequestMapping(value = "/add/liuyanmsg")
    @ResponseBody
    Result addliuyanmsg(HttpSession session,String msg,int level,int oppid){
        System.out.println("oppid:"+oppid);
        User user= (User) session.getAttribute("user");
        if(user==null){
            return Result.setError("请先登录");
        }
        level=level+1;
        String acc=user.getAccount();
        LiuYan news=new LiuYan();
        news.setAcc(acc);
        news.setLevel(level);
        news.setMsg(msg);
        news.setOppid(oppid);
        return service.addHuifu(news);
    }
}
