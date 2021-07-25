package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.Result;

public interface FriendmsgService {
    public Result getHuifu();
    public Result addHuifu(FriendsNews news);
    public Result getPinglun(int id,int lever);
    public Result delefriendmsg(int id);
    public Result addZan(int id);
}
