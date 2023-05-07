package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.TeacherService;
import com.web.back.domain.Teacher;
import com.web.back.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【teacher】的数据库操作Service实现
* @createDate 2023-05-07 13:10:23
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {

}




