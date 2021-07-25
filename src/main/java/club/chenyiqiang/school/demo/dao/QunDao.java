package club.chenyiqiang.school.demo.dao;

import club.chenyiqiang.school.demo.bean.News;
import club.chenyiqiang.school.demo.bean.Qun;
import club.chenyiqiang.school.demo.bean.QunNews;
import club.chenyiqiang.school.demo.bean.QunUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Mapper
@Repository
public interface QunDao {

    /*
    创建群
    CREATE TABLE `school`.`Untitled`  (
  `id` bigint NOT NULL,
  `name` varchar(30) NULL,
  `img` varchar(100) NULL,
  `qNotice` varchar(300) NULL,
  `max` int NULL,
  `has` int NULL,
  `hasLogin` int NULL,
  `time` bigint NULL,
  `stat` int(1) NULL,
  `endTime` bigint NULL,
  PRIMARY KEY (`id`)
);
     */
    /*
        群搜索|加群|
     */
    @Select("select count(*) from qun where id=#{qNum}")
    public int       isHasQun(long qNum);

    @Select("select *from qun where id=#{qNum}")
    public Qun findQun(long qNum);
    @Select("select   count(*) from qun_${qNum} where acc=#{acc}")
    public int isInQun(String acc,long qNum);

    @Insert("insert into qun_${qNum} values(#{acc},#{name},#{lastNum},-2,0)")
    public int shenqing(String acc,String name,int lastNum,long qNum);

    @Update("update qun set qNotice=#{qNotice} where id=#{qNum}")
    public int updateNotice(String qNotice,long qNum);

    @Select("select count(*) from qun_${qNum} where acc=#{acc} and role=1")
    public int queryQuanxian(String acc,long qNum);

    @Select("select id from qun where acc=#{acc}")
    public List<Long> getQun(String acc);

    @Select("select * from qun_${qNum}_jl where type=-2")
    public QunNews getSqAcc(long qNum);
    /*
        创建群--插入总群记录--创建群成员信息表--创建群消息表
     */
    @Insert("insert into qun values(#{qNum},#{name},#{img},#{acc},'',1000,0,0,#{time},1,0,'',#{type})")
    public int insertQun(long qNum,String name,String img,String acc,long time,String type);
    @Update("CREATE TABLE `qun_${qNum}`  (\n" +
            "  `acc` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,\n" +
            "  `name` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,\n" +
            "  `lastNum` int(11) NULL DEFAULT NULL,\n" +
            "  `role` int(1) NULL DEFAULT NULL,\n" +
            "  `hasLogin` int(1) NULL DEFAULT NULL,\n" +
            "  PRIMARY KEY (`acc`) USING BTREE\n" +
            ") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;")
    public int createQun(long qNum);
    @Update("CREATE TABLE `qun_${qNum}_jl`  (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `acc` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,\n" +
            "  `oppAcc` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,\n" +
            "  `msg` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,\n" +
            "  `img` int(11) NULL DEFAULT NULL,\n" +
            "  `file` int(11) NULL DEFAULT NULL,\n" +
            "  `type` int(11) NULL DEFAULT NULL,\n" +
            "  `time` bigint(20) NULL DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;")
    public int createQunJl(long qNum);


    @Update("update qun_${qNum}_jl set type=#{type},oppAcc=#{oppAcc} where id=#{id}")
    public int updateAdd(long qNum,String oppAcc,int type,int id);
    /*
        群成员加群--是否存在群--是否在群--群成员表添加--总群人数修改--群消息表添加
     */

    @Insert("insert into qun_${qNum} values(#{acc},#{name},#{lastNum},#{role},0)")
    public int addQun1(String acc,String name,long qNum,int lastNum,int role);
    @Update("update qun set has=has+1,accs=CONCAT(accs,#{acc}) where id=#{qNum}")
    public int addQun2(long qNum,String acc);
    @Update("update user set qun=CONCAT(qun,#{qNum}) where account=#{acc}")
    public int addQun3(String qNum,String acc);
    /*
        群成员发送消息
     */
    @Insert("insert into qun_${qNum}_jl(acc,oppAcc,msg,img,file,type,time) values(#{acc},#{oppAcc},#{msg},#{img},#{file},#{type},#{time})")
    public int addNews(long qNum,String acc,String oppAcc,String msg,int img,int file,int type,long time);

    /*
        群成员接收消息--到指定id开始,群成员表登记已经接收到的消息
     */
    @Select("select count(*) from qun_${qNum}_jl")
    public int recNewsNum(long qNum);
    @Select("select lastNum from qun_#{qNum} where acc=#{acc}")
    public int recLasNews(String acc,long qNum);
    @Select("select *from qun_${qNum}_jl where id>#{id}")
    public List<QunNews> recNews(long qNum, int id);
    @Update("update qun_${qNum} set lastNum=#{lastNum} where acc=#{acc}")
    public int recNewsSuccess(String acc,long qNum,int lastNum);

    @Select("select name from user where account=#{acc}")
    public String getAccName(String acc);

    @Select("select *from qun_${qNum}_jl where type>0")
    public List<QunNews> getHis(long qNum);
    @Select("select *from qun_${qNum}")
    public List<QunUser> getQunUser(long qNum);
    /*


     */


    public String getQunNews(long qNum, int num);
    /*
        群操作|
     */
    //
    public String recAddQun(long qNum);

    public String recJl(String acc,long qNum);
    public String recNumbers(long qNum);
    public String recQunInfo(String acc,long qnum);

    public boolean quitQun(String acc,long qnum);
    public boolean agreeAdd(String acc,long qnum,String qzhu);
    public String recHisJl(String acc,long qNum);
//    @Update("update qun set jl=concat(jl,#{str}),newsNum=newsNum+1 where id=#{qNum}")
//    public int send(long qNum,String str);
    public int send(String acc, long qNum, String str, File[] f);//表情包
    public int updateNotice(String acc,long qNum,String str);//更新群公告
    public int updateQname(String acc,long qNum,String str);
}
