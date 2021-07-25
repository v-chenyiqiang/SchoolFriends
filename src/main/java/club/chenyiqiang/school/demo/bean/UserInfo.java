package club.chenyiqiang.school.demo.bean;

public class UserInfo {
    private String account;
    private String name;
    private String phone;
    private String email;
    private String sheng;
    private String shi;
    private String xian;
    private String qm;//签名
    private String like;
    private long lastLoginTime;
    private String hisLoginTime;
    private int todayLoginNum;
    private int hisLoginNum;
    private String school;
    private String lastLoginLacation;
    private String hisLoginLatcation;
    private String details;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getQm() {
        return qm;
    }

    public void setQm(String qm) {
        this.qm = qm;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHisLoginTime() {
        return hisLoginTime;
    }

    public void setHisLoginTime(String hisLoginTime) {
        this.hisLoginTime = hisLoginTime;
    }

    public int getTodayLoginNum() {
        return todayLoginNum;
    }

    public void setTodayLoginNum(int todayLoginNum) {
        this.todayLoginNum = todayLoginNum;
    }

    public int getHisLoginNum() {
        return hisLoginNum;
    }

    public void setHisLoginNum(int hisLoginNum) {
        this.hisLoginNum = hisLoginNum;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLastLoginLacation() {
        return lastLoginLacation;
    }

    public void setLastLoginLacation(String lastLoginLacation) {
        this.lastLoginLacation = lastLoginLacation;
    }

    public String getHisLoginLatcation() {
        return hisLoginLatcation;
    }

    public void setHisLoginLatcation(String hisLoginLatcation) {
        this.hisLoginLatcation = hisLoginLatcation;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sheng='" + sheng + '\'' +
                ", shi='" + shi + '\'' +
                ", xian='" + xian + '\'' +
                ", qm='" + qm + '\'' +
                ", like='" + like + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", hisLoginTime='" + hisLoginTime + '\'' +
                ", todayLoginNum=" + todayLoginNum +
                ", hisLoginNum=" + hisLoginNum +
                ", school='" + school + '\'' +
                ", lastLoginLacation='" + lastLoginLacation + '\'' +
                ", hisLoginLatcation='" + hisLoginLatcation + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
