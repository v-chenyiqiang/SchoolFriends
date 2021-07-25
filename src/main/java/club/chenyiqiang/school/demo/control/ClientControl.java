package club.chenyiqiang.school.demo.control;

import club.chenyiqiang.school.demo.bean.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClientControl {
    @RequestMapping(value = "/get_service", method = {RequestMethod.POST})
    @ResponseBody
    Result get_service(){

        return null;
    }
}
