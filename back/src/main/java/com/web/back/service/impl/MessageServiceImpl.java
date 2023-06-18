package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Message;
import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.web.back.domain.result.ReMessage;
import com.web.back.service.MessageService;
import com.web.back.mapper.MessageMapper;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author Dell
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-06-19 02:11:36
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Resource
    MessageMapper messageMapper;
    @Override
    public ResposeResult add_message(Integer sender_id, Integer receiver_id, String content, Integer identity) {
        Message message = new Message(sender_id, receiver_id, content, identity);
        messageMapper.insert(message);
        return new ResposeResult(1, "发送成功");
    }


    //identity1为接收者的身份，identity2为发送者的身份
    @Override
    public List<ReMessage> get_message(Integer sender_id, Integer receiver_id, Integer identity1, Integer identity2) {
        List<Student>  studentList = new ArrayList<>();
        List<Teacher> teacherList = new ArrayList<>();
        if(identity1 == 0) {
            studentList = messageMapper.get_student_friend(sender_id, receiver_id);
        }
        else {
            teacherList = messageMapper.get_teacher_friend(sender_id, receiver_id);
        }
        List<Student> studentList1 = new ArrayList<>();
        List<Teacher> teacherList1 = new ArrayList<>();
        if(identity2 == 0) {
            studentList1 = messageMapper.get_student_friend(receiver_id, sender_id);
        }
        else {
            teacherList1 = messageMapper.get_teacher_friend(receiver_id, sender_id);
        }
        QueryWrapper queryWrapper = new QueryWrapper<>();
        //查询我发送的信息
        queryWrapper.eq("sender_id", sender_id);
        queryWrapper.eq("receiver_id", receiver_id);
        queryWrapper.eq("identity", identity1);
        List<Message> messageList = messageMapper.selectList(queryWrapper);
        //组装我发送的全部信息
        List<ReMessage> reMessageList = new ArrayList<>();
        for(Integer i = 0; i < messageList.size(); i++) {
            String name = identity1 == 0 ? studentList.get(0).getName() : teacherList.get(0).getName();
            ReMessage reMessage = new ReMessage(sender_id, receiver_id, name, messageList.get(i).getContent(), (Date)messageList.get(i).getTime());
            reMessageList.add(reMessage);
        }
        //查询我接收的信息
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sender_id", receiver_id);
        queryWrapper.eq("receiver_id", sender_id);
        queryWrapper.eq("identity", identity2);
        messageList = messageMapper.selectList(queryWrapper);
        //组装我接收的全部信息
        for(Integer i = 0; i < messageList.size(); i++) {
            String name = identity2 == 0 ? studentList1.get(0).getName() : teacherList1.get(0).getName();
            ReMessage reMessage = new ReMessage(receiver_id, sender_id, name, messageList.get(i).getContent(), (Date) messageList.get(i).getTime());
            reMessageList.add(reMessage);
        }
        //将reMessageList按照时间排序
        for(Integer i = 0; i < reMessageList.size(); i++) {
            for(Integer j = i + 1; j < reMessageList.size(); j++) {
                if(reMessageList.get(i).getSendTime().compareTo(reMessageList.get(j).getSendTime()) > 0) {
                    ReMessage reMessage = reMessageList.get(i);
                    reMessageList.set(i, reMessageList.get(j));
                    reMessageList.set(j, reMessage);
                }
            }
        }
        return reMessageList;
    }
}




