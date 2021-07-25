package club.chenyiqiang.school.demo.service;

import club.chenyiqiang.school.demo.bean.Result;


public interface QunService {

    /*
        登录|在线
     */
    public Result isInQun(String acc,long qNum);
    public Result addQun(String acc,String oppAcc,long qNum);
    public Result sendByUser(String acc,String str,long qNum,long time);
    public Result sendFileByUser(String acc,long qNum,String str,int num,long time);
    public Result create(String acc,String name,String img,long qNum,String type);
    public Result recNews(String acc,long qNum);
    public Result findByqNum(long qNum);
    public Result isHasQun(long qNum);
    public Result shenqing(String acc,long qNum);
    public Result sendBySys(String acc,String oppAcc,String str, int type,long qNum,long time);
    public Result dealAddQun(String acc,String oppAcc,long qNum,int id,int doing);
    public Result dealSq(String acc);
    public Result init_getqun(String acc);
    public Result getQunUser(long qNum);
    public Result updateNotice(String notice,String acc,long qNum);
    public Result getHis(long qNum);
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
