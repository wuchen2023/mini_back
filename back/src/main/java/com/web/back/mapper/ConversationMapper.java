package com.web.back.mapper;

import com.web.back.domain.Conversation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【conversation】的数据库操作Mapper
* @createDate 2023-06-19 01:52:19
* @Entity com.web.back.domain.Conversation
*/
public interface ConversationMapper extends BaseMapper<Conversation> {

    //查询根据chat_id和identity查询student和teacher表中的name，如果identity为0就是student表，为1就是teacher表
    @Select("select s.name, s.id from student s where s.id in (select chat_id from conversation where user_id = #{userId} and identity = 0)")
    public List<Student> get_student_conversation(Integer userId);

    @Select("select t.name, t.id from teacher t where t.id in (select chat_id from conversation where user_id = #{userId} and identity = 1)")
    public List<Teacher> get_teacher_conversation(Integer userId);

}




