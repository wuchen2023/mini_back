package com.web.back.mapper;

import com.web.back.domain.Group;
import com.web.back.domain.Grouping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【grouping】的数据库操作Mapper
* @createDate 2023-05-07 13:09:58
* @Entity generator.domain.Grouping
*/
public interface GroupingMapper extends BaseMapper<Grouping> {
    @Select("SELECT * from `grouping` where student_id=#{student_id} and student_group_id=#{student_group_id}")
    Grouping get_one(Integer student_id, Integer student_group_id);

    @Delete("Delete from `grouping` where student_id=#{student_id} and student_group_id=#{student_group_id}")
    void delete_one(Integer student_id, Integer student_group_id);

    @Select("SELECT `grouping`.*, student.name from `grouping`, student where student.id=`grouping`.student_id and student_group_id=#{student_group_id}")
    List<Grouping> get_all(Integer student_group_id);

    @Insert("INSERT into `grouping`(student_id, student_group_id) values(#{student_id}, #{student_group_id})")
    void insert_one(Integer student_id, Integer student_group_id);

}




