package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.service.StudentService;
import com.web.back.domain.Student;
import com.web.back.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-05-07 13:10:05
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

}




