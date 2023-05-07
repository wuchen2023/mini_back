package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.StudentClassService;
import com.web.back.domain.StudentClass;
import com.web.back.mapper.StudentClassMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【student_class】的数据库操作Service实现
* @createDate 2023-05-07 13:10:07
*/
@Service
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass>
    implements StudentClassService {

}




