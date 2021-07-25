package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.Result;
import club.chenyiqiang.school.demo.bean.User;


public interface UserService {

    /*
        登录|在线
     */
    public Result check(String acc, String pass);
    public Result checkRoot(String acc, String pass);
    public Result send_yzm(String acc,String email);
    public Result update_pass(String acc,String pass,String email,String yzm);
    public Result logon(User user);
    public User getUser(String acc);
    public Result initLogin(String acc,String ip,long time);
    public Result init_24();
//
//    /*
//        群搜索|加群|
//     */
//    public boolean isHasQun(String acc,long qNum);
//    public Qun findQun(long qNum);
//    public int addQun(String acc,long qNum,String liuyan,long time);
//
//    /*
//        群操作|
//     */
//    public String recAddQun(long qNum);
//    public String recJl(String acc,long qNum);
//    public String recNumbers(long qNum);
//    public String recQunInfo(String acc,long qnum);
//
//    public boolean quitQun(String acc,long qnum);
//    public boolean agreeAdd(String acc,long qnum,String qzhu);
//    public String recHisJl(String acc,long qNum);
//    public int send(String acc,long qNum,String str);
//    public int send(String acc, long qNum, String str, File[] f);//表情包
//    public int updateNotice(String acc,long qNum,String str);//更新群公告
//    public int updateQname(String acc,long qNum,String str);






}
