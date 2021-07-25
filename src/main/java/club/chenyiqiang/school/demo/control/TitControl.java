package club.chenyiqiang.school.demo.control;


import club.chenyiqiang.school.demo.bean.Notice;
import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.service.NoticeService;
import club.chenyiqiang.school.demo.service.UserService;
import club.chenyiqiang.school.demo.utils.SchoolNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TitControl {
    @Autowired
    private NoticeService service;
    @RequestMapping("/json/getTits")
    @ResponseBody
    Result getTits(){
        return service.getTit();
    }
    @RequestMapping("/delete/deleteTit")
    @ResponseBody
    Result deleteTit(int id){
        return service.deleTit(id);
    }
    @RequestMapping("/add/autoUpdate")
    @ResponseBody
    Result autoUpdate(){
        List<Notice> notices=new SchoolNews().getList();
        return service.autoUpdate(notices);
    }
}
