package club.chenyiqiang.school.demo.dao;

import club.chenyiqiang.school.demo.bean.Qun;
import club.chenyiqiang.school.demo.bean.User;
import club.chenyiqiang.school.demo.bean.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
@Mapper
@Repository
public interface UserDao {

    /*
        登录|在线
     */

    @Insert("insert into user(stat,name,account,password,email,qun) values(#{stat},#{name},#{account},#{password},#{email},'123456789,')")
    public int initAcc1(User user);

    @Insert("insert into userinfo values(#{account},#{name},'',#{email},'','','','','','','',0,0,'南昌工程学院','','','')")
    public int initAcc2(User user);
    @Update("updata userinfo set todayLoginNum=''")
    public int init_24();
    @Update("update userinfo set lastLoginTime=#{time},hisLoginTime=CONCAT(hisLoginTime,#{time}),todayLoginNum=todayLoginNum+1,hisLoginNum=hisLoginNum+1,lastLoginLacation=#{ip},hisLoginLacation=CONCAT(hisLoginLacation,#{ip}) where account=#{acc}")
    public int initlogin(String acc,String ip,String time);   //,CONCAT(lastLoginLacation,',${ip}')//CONCAT(hisLoginTime,',#{time}'),
    @Select("select stat from user where account=#{acc}")
    public int isStat(String acc);
    @Select("select count(*) from user where account=#{acc}")
    public int isHasAcc(String acc);
    @Select("select count(*) from user where account=#{acc} and password=#{pass}")
    public int check(String acc,String pass);
    @Select("select count(*) from user where account=#{acc} and password=#{pass} and stat=999")
    public int checkRoot(String acc,String pass);
    @Select("select count(*) from user where account=#{acc} and email=#{email}")
    public int checkEmail(String acc,String email);

    @Update("update user set password=#{pass} where account=#{acc}")
    public int updatePass(String acc,String pass);

    @Select("select *from user where account=#{acc}")
    public User getUser(String acc);

    @Select("select name from user where account=#{acc}")
    public String getAccName(String acc);

    @Select("select qun from user where account=#{acc}")
    public String getQunByAcc(String acc);
}
