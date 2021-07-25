package club.chenyiqiang.school.demo.dao;


import club.chenyiqiang.school.demo.bean.FriendsNews;
import club.chenyiqiang.school.demo.bean.LiuYan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LiuyanDao {
    @Select("select *from liuyan where level=1")
    public List<LiuYan> getHuifu();

    @Select("select *from liuyan where oppid=#{id} and level=#{level} order by id")
    public List<LiuYan> getPingLun(int id,int level);

    @Insert("insert into liuyan(acc,msg,time,level,oppid,zan,plNum) values(#{acc},#{msg},#{time},#{level},#{oppid},0,0)")
    public int addHuifu(LiuYan news);

    @Update("update liuyan set plNum=plNum+1 where id=#{id}")
    public int updatePlNum(int id);

    @Update("update liuyan set zan=zan+1 where id=#{id}")
    public int updateZanNum(int id);

    @Delete("delete from liuyan where id=#{id}")
    public int deleteByid(int id);

}
