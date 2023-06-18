package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Conversation;
import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.web.back.domain.result.Cov;
import com.web.back.service.ConversationService;
import com.web.back.mapper.ConversationMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author Dell
* @description 针对表【conversation】的数据库操作Service实现
* @createDate 2023-06-19 01:52:19
*/
@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation>
    implements ConversationService{

    @Resource
    ConversationMapper conversationMapper;
    @Override
    public ResposeResult add_conversation(Integer userId, Integer chatId, Integer identity) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("chat_id", chatId);
        queryWrapper.eq("identity", identity);
        Conversation conversation = conversationMapper.selectOne(queryWrapper);
        if(conversation == null)
        {
            conversation = new Conversation(userId, chatId, identity);
            conversationMapper.insert(conversation);
            return new ResposeResult(1, "会话创建成功");
        }
        else
        {
            return new ResposeResult(0, "会话已存在");
        }
    }

    @Override
    public List<Cov> get_all_conversation(Integer userId) {
        List<Student> studentList = conversationMapper.get_student_conversation(userId);
        List<Teacher> teacherList = conversationMapper.get_teacher_conversation(userId);
        List<Cov> covList = new ArrayList<>();
        for(Student student : studentList)
        {
            Cov cov = new Cov(student.getId(), student.getName(), "学生相关会话");
            covList.add(cov);
        }
        for(Teacher teacher : teacherList)
        {
            Cov cov = new Cov(teacher.getId(), teacher.getName(), "教师相关会话");
            covList.add(cov);
        }
        return covList;
    }


}




