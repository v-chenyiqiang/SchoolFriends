package club.chenyiqiang.school.demo.dao;


import club.chenyiqiang.school.demo.bean.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TitDao {

    @Select("select *from notices")
    public List<Notice> getTit();

    @Insert("insert into notices(tit,time,url) values(#{tit},#{time},#{url})")
    public int add(Notice notice);
    @Delete("delete from notices where id=#{id}")
    public int delete(int id);

}
