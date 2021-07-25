package club.chenyiqiang.school.demo.bean;

import com.alibaba.fastjson.JSON;

public class SystemNews {
    private String msg;
    private int type;
    private long time;
    private String acc;
    private String opp;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getOpp() {
        return opp;
    }

    public void setOpp(String opp) {
        this.opp = opp;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
