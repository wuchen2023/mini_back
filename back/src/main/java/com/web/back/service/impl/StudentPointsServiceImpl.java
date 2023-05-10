package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.StudentPoints;
import com.web.back.service.StudentPointsService;
import com.web.back.mapper.StudentPointsMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【student_points】的数据库操作Service实现
* @createDate 2023-05-10 13:30:25
*/
@Service
public class StudentPointsServiceImpl extends ServiceImpl<StudentPointsMapper, StudentPoints>
    implements StudentPointsService{

}




