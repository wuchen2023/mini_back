package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Student;
import com.web.back.domain.Teacher;
import com.web.back.domain.result.Fridend;
import com.web.back.domain.user_relation;
import com.web.back.service.user_relationService;
import com.web.back.mapper.user_relationMapper;
import com.web.back.state.ResposeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Dell
* @description 针对表【user_relation】的数据库操作Service实现
* @createDate 2023-06-19 00:43:43
*/
@Service
public class user_relationServiceImpl extends ServiceImpl<user_relationMapper, user_relation>
    implements user_relationService{

    @Resource
    user_relationMapper user_relationMapper;

    //TODO 这个方法和表还需要添加一个userid 的身份，不然有问题
    @Override
    public ResposeResult add_friend(Integer userId, Integer friendId, Integer identity, Integer identity_user) {
        user_relation user_relation = new user_relation(userId, friendId, identity, identity_user);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("friend_id", friendId);
        queryWrapper.eq("identity", identity);
        queryWrapper.eq("identity_user", identity_user);
        if(user_relationMapper.exists(queryWrapper))
        {
            return new ResposeResult(0, "已经添加过该好友");
        }
        user_relationMapper.insert(user_relation);
        return new ResposeResult(1, "添加好友成功");
    }

    @Override
    public ResposeResult delete_friend(Integer userId, Integer friendId, Integer identity, Integer identity_user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("friend_id", friendId);
        queryWrapper.eq("identity", identity);
        queryWrapper.eq("identity_user", identity_user);
        if(!user_relationMapper.exists(queryWrapper))
        {
            return new ResposeResult(0, "不存在该好友");
        }
        user_relationMapper.delete(queryWrapper);
        return new ResposeResult(1, "删除好友成功");
    }

    @Override
    public List<Fridend> get_friend(Integer userId, Integer identity_user) {

        List<Student> student_friend = user_relationMapper.get_student_friend(userId, identity_user);
        List<Teacher> teacher_friend = user_relationMapper.get_teacher_friend(userId, identity_user);
        List<Fridend> fridendList = new java.util.ArrayList<>();
        for(Student student : student_friend)
        {
            fridendList.add(new Fridend(student.getId(), student.getName(), "学生"));
        }
        for(Teacher teacher : teacher_friend)
        {
            fridendList.add(new Fridend(teacher.getId(), teacher.getName(), "教师"));
        }
        return fridendList;
    }
}




