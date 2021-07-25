package club.chenyiqiang.school.demo.utils;

import club.chenyiqiang.school.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务
    @Autowired
    private UserService service;
    @Scheduled(cron = "0 59 23 * * ?")
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        service.init_24();
        System.out.println("每天24点初始化更新");
    }
}