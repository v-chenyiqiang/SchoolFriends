package club.chenyiqiang.school.demo.dao;


import club.chenyiqiang.school.demo.bean.FriendsNews;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendDao {
    @Select("select *from friendmsg where level=1")
    public List<FriendsNews> getHuifu();

    @Select("select *from friendmsg where oppid=#{id} and level=#{level} order by id")
    public List<FriendsNews> getPingLun(int id,int level);

    @Insert("insert into friendmsg(acc,msg,time,level,oppid,zan,tit,plNum) values(#{acc},#{msg},#{time},#{level},#{oppid},0,#{tit},0)")
    public int addHuifu(FriendsNews news);

    @Update("update friendmsg set plNum=plNum+1 where id=#{id}")
    public int updatePlNum(int id);

    @Update("update friendmsg set zan=zan+1 where id=#{id}")
    public int updateZanNum(int id);

    @Delete("delete from friendmsg where id=#{id}")
    public int deleteByid(int id);


}
