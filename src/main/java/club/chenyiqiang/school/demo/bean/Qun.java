package club.chenyiqiang.school.demo.bean;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class Qun {
    private long id;
    private String name;
    private String img;
    private String acc;
    private String qNotice;//群通知,群通告
    private int max;
    private int has;
    private int hasLogin;
    private String time;
    private int stat;
    private String endTime;
    private String accs;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getqNotice() {
        return qNotice;
    }

    public void setqNotice(String qNotice) {
        this.qNotice = qNotice;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getHas() {
        return has;
    }

    public void setHas(int has) {
        this.has = has;
    }

    public int getHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(int hasLogin) {
        this.hasLogin = hasLogin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAccs() {
        return accs;
    }

    public void setAccs(String accs) {
        this.accs = accs;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
