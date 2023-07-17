package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.StudentGroupService;
import com.web.back.domain.StudentGroup;
import com.web.back.mapper.StudentGroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Dell
* @description 针对表【student_group】的数据库操作Service实现
* @createDate 2023-05-07 13:10:14
*/
@Service
public class StudentGroupServiceImpl extends ServiceImpl<StudentGroupMapper, StudentGroup>
    implements StudentGroupService {
    @Resource
    StudentGroupMapper studentGroupMapper;
    @Override
    public List<Integer> findAllStuGroupIds(Integer teacherGroupId){
        return studentGroupMapper.findAllStuGroupIds(teacherGroupId);

    }
}




