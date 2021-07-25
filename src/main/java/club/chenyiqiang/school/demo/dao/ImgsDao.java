package club.chenyiqiang.school.demo.dao;


import club.chenyiqiang.school.demo.bean.Imgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImgsDao {
    @Select("select *from imgs order by id")
    public List<Imgs> getImgs();

    @Update("update imgs set id=#{theNew} where id=#{old}")
    public int updateId(int old,int theNew);
    @Insert("insert into imgs(id,time,url) values(#{id},#{time},#{url})")
    public int addImg(Imgs imgs);
    @Select("select count(*)from imgs where url=#{url}")
    public int isHas(String url);
    @Select("select count(*)from imgs where id=#{id}")
    public int isHasById(int id);
    @Select("select count(*)from imgs")
    public int getID();

}
