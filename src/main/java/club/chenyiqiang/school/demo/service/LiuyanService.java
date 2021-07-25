package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.LiuYan;
import club.chenyiqiang.school.demo.bean.Result;

public interface LiuyanService {
    public Result getHuifu();
    public Result addHuifu(LiuYan news);
    public Result getPinglun(int id,int lever);
    public Result deleteLy(int id);
    public Result addZan(int id);
}
