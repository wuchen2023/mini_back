package com.web.back.mapper;

import com.web.back.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【message】的数据库操作Mapper
* @createDate 2023-06-19 02:11:36
* @Entity com.web.back.domain.Message
*/
public interface MessageMapper extends BaseMapper<Message> {

    //查询根据receiver_id和identity查询student和teacher表中的name，如果identity为0就是student表，为1就是teacher表
    @Select("select s.name, s.id from student s where s.id in (select receiver_id from message where sender_id = #{sender_id} and receiver_id = #{receiver_id} and identity = 0)")
    public List<Student> get_student_friend(Integer sender_id, Integer receiver_id);

    @Select("select t.name, t.id from teacher t where t.id in (select receiver_id from message where sender_id = #{sender_id} and  receiver_id = #{receiver_id} and identity = 1)")
    public List<Teacher> get_teacher_friend(Integer sender_id, Integer receiver_id);
}




