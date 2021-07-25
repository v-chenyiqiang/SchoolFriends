package club.chenyiqiang.school.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SchoolFriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolFriendsApplication.class, args);
    }

}
