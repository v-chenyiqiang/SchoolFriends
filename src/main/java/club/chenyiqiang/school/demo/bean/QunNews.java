package club.chenyiqiang.school.demo.bean;

import com.alibaba.fastjson.JSON;

public class QunNews {
    private int id;
    private String acc;
    private String oppAcc;
    private String msg;
    private int img;
    private int file;
    private int type;
    private String time;
    private int self;
    private String acc_name;
    private long qNum;
    private String simTime;

    public String getSimTime() {
        return simTime;
    }

    public void setSimTime(String simTime) {
        this.simTime = simTime;
    }

    public long getqNum() {
        return qNum;
    }

    public void setqNum(long qNum) {
        this.qNum = qNum;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public int getSelf() {
        return self;
    }

    public void setSelf(int self) {
        this.self = self;
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

    public String getOppAcc() {
        return oppAcc;
    }

    public void setOppAcc(String oppAcc) {
        this.oppAcc = oppAcc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
