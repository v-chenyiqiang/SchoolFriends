package club.chenyiqiang.school.demo.control;

import club.chenyiqiang.school.demo.bean.News;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class AccControl {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/add/logon", method = {RequestMethod.GET})
    @ResponseBody
    Result logon(String acc,String name,String email,String pass){
        System.out.println("yz_login:"+acc+"  "+pass+"   "+name+"   "+email);
        User user=new User();
        user.setStat(0);
        user.setPassword(pass);
        user.setAccount(acc);
        user.setEmail(email);
        user.setName(name);
        return service.logon(user);
    }


    @RequestMapping(value = "/yz/login", method = {RequestMethod.POST})
    @ResponseBody
    Result login(String acc , String pass,HttpSession session,HttpServletRequest request){
        System.out.println("yz_login:"+acc+"  "+pass);
        Result result=service.check(acc,pass);
        if (result.getCode()==1){
            User user=service.getUser(acc);
            session.setAttribute("user",user);
            String ip=request.getRemoteAddr();
            System.out.println("ip:"+ip);
            String url=session.getAttribute("addr").toString();
            if(url!=null)
                result=Result.setType("redirect",url);
          //  session.setAttribute("addr","/chat.html");
            service.initLogin(acc,ip,new Date().getTime());
        }
        return result;
    }
    @RequestMapping(value = "/yz/loginRoot", method = {RequestMethod.POST})
    @ResponseBody
    Result loginRoot(String acc , String pass,HttpSession session){
        System.out.println("yz_login:"+acc+"  "+pass);
        Result result=service.checkRoot(acc,pass);
        if (result.getCode()==1){
            User user=service.getUser(acc);
            session.setAttribute("user",user);
            String url=session.getAttribute("addr").toString();
            if(url!=null)
                result=Result.setType("redirect",url);
        }
        return result;
    }
    @RequestMapping(value = "/yz/send_yzm")
    @ResponseBody
    Result send_yzm(String acc,String email){
        System.out.println("send_yzm:"+acc+"  "+email);
        return service.send_yzm(acc,email);
    }
    @RequestMapping(value = "/yz/update_acc", method = {RequestMethod.POST})
    @ResponseBody
    Result update_acc(String acc,String pass,String email,String yzm){

        return service.update_pass(acc,pass,email,yzm);
    }

}
