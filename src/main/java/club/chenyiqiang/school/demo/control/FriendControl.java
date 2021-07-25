package club.chenyiqiang.school.demo.control;


import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.LiuYan;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.service.FriendmsgService;
import club.chenyiqiang.school.demo.service.LiuyanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class FriendControl {
    @Autowired
    private FriendmsgService service;

    @RequestMapping(value = "/json/friendmsg")
    @ResponseBody
    Result friendmsg(){
        return service.getHuifu();
    }
    ///json/friendmsgByoppid
    @RequestMapping(value = "/delete/delefriendmsg")
    @ResponseBody
    Result delefriendmsg(int id){

        return service.delefriendmsg(id);
    }
    @RequestMapping(value = "/json/friendmsgByoppid")
    @ResponseBody
    Result friendmsgByid(int id,int level){
        System.out.println("当前的层数是:"+level);
        level=level+1;
        System.out.println("返回的层数是:"+level);
        return service.getPinglun(id,level);
    }

    @RequestMapping(value = "/add/friendmsgZanByid")
    @ResponseBody
    Result friendmsgZanByid(int id){
        System.out.println("当前id是:"+id);
        return service.addZan(id);
    }
// method = {RequestMethod.POST}
    @RequestMapping(value = "/add/friendmsg")
    @ResponseBody
    Result addfriendmsg(HttpSession session,String msg,int level,int oppid,String tit){
        System.out.println("oppid:"+oppid);
        User user= (User) session.getAttribute("user");
        if(user==null){
            return Result.setError("请先登录");
        }
        level=level+1;
        String acc=user.getAccount();
        FriendsNews news=new FriendsNews();
        news.setAcc(acc);
        news.setLevel(level);
        news.setMsg(msg);
        news.setOppid(oppid);
        news.setTit(tit);
        return service.addHuifu(news);
    }
}
