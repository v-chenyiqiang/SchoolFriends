package club.chenyiqiang.school.demo.bean;

import com.alibaba.fastjson.JSON;

public class QunUser {
    private String acc;
    private String name;
    private int lastNum;
    private int role;
    private int hasLogin;

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLastNum() {
        return lastNum;
    }

    public void setLastNum(int lastNum) {
        this.lastNum = lastNum;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(int hasLogin) {
        this.hasLogin = hasLogin;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
