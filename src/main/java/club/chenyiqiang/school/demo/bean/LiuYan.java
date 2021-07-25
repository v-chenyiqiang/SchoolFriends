package club.chenyiqiang.school.demo.bean;

import com.alibaba.fastjson.JSON;

public class LiuYan {
    private int id;
    private String acc;
    private String msg;
    private String time;
    private int level;
    private int oppid;
    private int zan;
    private String name;
    private int plNum;

    public int getPlNum() {
        return plNum;
    }

    public void setPlNum(int plNum) {
        this.plNum = plNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOppid() {
        return oppid;
    }

    public void setOppid(int oppid) {
        this.oppid = oppid;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
