package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.Student;
import com.web.back.service.StudentClassService;
import com.web.back.domain.StudentClass;
import com.web.back.mapper.StudentClassMapper;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;
import com.web.back.viewmodel.admin.studentclass.StudentClassResponseVM;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【student_class】的数据库操作Service实现
* @createDate 2023-05-07 13:10:07
*/
@Service
@Slf4j
public class StudentClassServiceImpl extends ServiceImpl<StudentClassMapper, StudentClass>
    implements StudentClassService {

//    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    private final StudentClassMapper studentClassMapper;

    @Autowired
    public StudentClassServiceImpl(StudentClassMapper studentClassMapper){
//        super(studentClassMapper);
        this.studentClassMapper = studentClassMapper;
    }

    @Override
    public PageInfo<StudentClass> page(StudentClassPageRequestVM requestVM){
        return PageHelper.startPage(requestVM.getPageIndex(),requestVM.getPageSize(),"id desc").doSelectPageInfo(() ->
                studentClassMapper.page(requestVM));
    }

    @Override
    public StudentClass get_student(String className){
        try{

            StudentClass studentClass = studentClassMapper.get_student(className);
            if(studentClass.getStudentId()==null){
                throw new Exception();
            }
            log.info("根据课程名随机查到的学生id为："+studentClass.getStudentId());
            return studentClass;
        }catch (Exception e){
            return null;
        }
    }
}




