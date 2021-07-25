package club.chenyiqiang.school.demo.control;

import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.service.QunService;
import club.chenyiqiang.school.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

@Controller
public class TestQun {

    @Autowired
    private QunService service;

    @RequestMapping("/yz/send_str")
    @ResponseBody
    Result send(HttpSession session,long qNum,String chat_msg){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        System.out.println("yz_send:"+acc+"  "+qNum);
        if(chat_msg==null||chat_msg.length()==0||chat_msg.length()>1000){
            return Result.setError("拒绝:字节数为0或者大于1000");
        }
        return service.sendByUser(acc,chat_msg,qNum,new Date().getTime());
    }

    @RequestMapping("/add/qNumByqNum")
    @ResponseBody
    Result addqNumByqNum(HttpSession session,String tongyi,long qNum){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        System.out.println("qNumByqNum:"+acc+"  "+qNum);
        return service.addQun(acc,tongyi,qNum);
    }
    @RequestMapping("/add/shenqingByqNum")
    @ResponseBody
    Result shenqingByqNum(HttpSession session,long qNum){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        System.out.println("qNumByqNum:"+acc+"  "+qNum);
        return service.shenqing(acc,qNum);
    }


    /*
    *处理加群申请
     */
    @RequestMapping("/add/dealAddQun")
    @ResponseBody
    Result dealAddQun(String acc,long qNum,String oppAcc,int id,int doing){

        System.out.println("qNumByqNum:"+acc+"  "+qNum);
        return service.dealAddQun(acc,oppAcc,qNum,id,doing);
    }

    /*
    *搜索群通过群号码
     */
    @RequestMapping("/json/searchByqNum")
    @ResponseBody
    Result searchByqNum(long qNum){
        System.out.println("searchByqNum:"+qNum);
        return service.findByqNum(qNum);
    }
    /*
    *验证是否在群
     */
    @RequestMapping("/yz_qun")
    @ResponseBody
    Result name(String acc,long qNum){
        System.out.println("yz_qun:"+acc+"  "+qNum);
        return service.isInQun(acc,qNum);
    }

    /*
    *创建群，注意：群头像默认
     */
    @RequestMapping("/add/createQun")
    @ResponseBody
    Result createQun(HttpSession session,String type,String name){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        String img="bg.jpg";//默认
        Random r=new Random();
        long qNum=0;
        String num="";
        int code=-1;
        int len=4+r.nextInt(4);
        while (code!=2) {
            num="";
            for (int i = 0; i < len; i++) {
                num += r.nextInt(9);
            }
            qNum = Long.valueOf(num);
            code = service.isHasQun(qNum).getCode();
        }
        //String acc,String name,String img,long qNum,String type
        return service.create(acc,name,img,qNum,type);
    }
    @RequestMapping("/yz/recSys")
    @ResponseBody
    Result recSys(HttpSession session){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
       // System.out.println("yz_rec:"+acc);
        return service.dealSq(acc);
    }
    @RequestMapping("/yz/rec")
    @ResponseBody
    Result recNews(HttpSession session,long qNum){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
       // System.out.println("yz_rec:"+acc+"  "+qNum);
        return service.recNews(acc,qNum);
    }

    @RequestMapping("/get/his")
    @ResponseBody
    Result his(HttpSession session,long qNum){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        if(service.isInQun(acc,qNum).getCode()!=1){
            return Result.setError("无权限");
        }
        // System.out.println("yz_rec:"+acc+"  "+qNum);
        return service.getHis(qNum);
    }
////json/init_getQuns
@RequestMapping("/json/init_getQuns")
@ResponseBody
Result init_getQuns(HttpSession session){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
    System.out.println("init_getQuns");
    return service.init_getqun(acc);
}
@RequestMapping("/json/init_getQunUser")
@ResponseBody
Result init_getQunUser(long qNum){
    System.out.println("init_getQunUser");
    return service.getQunUser(qNum);
}
    @RequestMapping("/json/init_getQun")
    @ResponseBody
    Result init_getQun(long qNum){
        System.out.println("init_getQun:");
        return service.findByqNum(qNum);
    }
    @RequestMapping("/update/qNotice")
    @ResponseBody
    Result qNotice(HttpSession session,long qNum,String qNotice){
        User user=(User)session.getAttribute("user");
        String acc=user.getAccount();
        return service.updateNotice(qNotice,acc,qNum);
    }
    @RequestMapping("/json/firstRec")
    @ResponseBody
    Result firstRec(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            Result result=Result.setType("redirect","/login.html");
            result.setCode(2);
            result.setMsg("未登录");
            return result;
        }
        return null;
    }

}
