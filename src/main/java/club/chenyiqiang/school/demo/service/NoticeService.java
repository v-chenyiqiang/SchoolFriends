package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.Notice;
import club.chenyiqiang.school.demo.bean.Result;

import java.util.List;

public interface NoticeService {
    public Result getTit();
    public Result deleTit(int id);
    public Result autoUpdate(List<Notice> notices);
}
