package club.chenyiqiang.school.demo.bean;

public class Grade {
    private int year;
    private int sYears;//
    private int sGrade;
    private int num;
    private String tName;//班主任名字
    private String tFriends;
    private String tTeachers;//所有老师
    private long cTime;
    private String cName;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getsYears() {
        return sYears;
    }

    public void setsYears(int sYears) {
        this.sYears = sYears;
    }

    public int getsGrade() {
        return sGrade;
    }

    public void setsGrade(int sGrade) {
        this.sGrade = sGrade;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettFriends() {
        return tFriends;
    }

    public void settFriends(String tFriends) {
        this.tFriends = tFriends;
    }

    public String gettTeachers() {
        return tTeachers;
    }

    public void settTeachers(String tTeachers) {
        this.tTeachers = tTeachers;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "year=" + year +
                ", sYears=" + sYears +
                ", sGrade=" + sGrade +
                ", num=" + num +
                ", tName='" + tName + '\'' +
                ", tFriends='" + tFriends + '\'' +
                ", tTeachers='" + tTeachers + '\'' +
                ", cTime=" + cTime +
                ", cName='" + cName + '\'' +
                '}';
    }
}
