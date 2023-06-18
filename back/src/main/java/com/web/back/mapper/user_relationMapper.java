package com.web.back.mapper;

import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.web.back.domain.result.Fridend;
import com.web.back.domain.user_relation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Hp
* @description 针对表【user_relation】的数据库操作Mapper
* @createDate 2023-06-19 00:43:43
* @Entity com.web.back.domain.user_relation
*/
public interface user_relationMapper extends BaseMapper<user_relation> {


    //查询根据friend_id和identity查询student和teacher表中的name，如果identity为0就是student表，为1就是teacher表
    @Select("select s.name, s.id from student s where s.id in (select friend_id from user_relation where user_id = #{userId} and identity = 0)")
    public List<Student> get_student_friend(Integer userId);

    @Select("select t.name, t.id from teacher t where t.id in (select friend_id from user_relation where user_id = #{userId} and identity = 1)")
    public List<Teacher> get_teacher_friend(Integer userId);
}




