package club.chenyiqiang.school.demo.control;


import club.chenyiqiang.school.demo.bean.Imgs;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.service.ImgsService;
import club.chenyiqiang.school.demo.utils.SchoolImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
public class SchoolImgControl {

    @Autowired
    private ImgsService service;

    @RequestMapping(value = "/img/getimsgs")
    @ResponseBody
    public Result getimags(){
        return service.getImgs();
    }


    @RequestMapping(value = "/img/updateId")
    @ResponseBody
    public Result updateId(int old,int theNew){
        return service.updateID(old,theNew);
    }


    @RequestMapping(value = "/img/addimsgs")
    @ResponseBody
    public Result addimags(HttpSession session){
        User user= (User) session.getAttribute("user");
        String acc=user.getAccount();
        if(acc==null){
            return Result.setError("无权限");
        }
        List<String> list= new SchoolImg().getList();
        for (int i=0;i<list.size();i++){
            Imgs imgs=new Imgs();
            imgs.setUrl(list.get(i));
            imgs.setTime(new Date().getTime()+"");
            service.addImgs(imgs);
        }
        return Result.setSuccess("已经完成");
    }

}
