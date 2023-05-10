package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.StudentClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.viewmodel.admin.studentclass.StudentClassPageRequestVM;

/**
* @author Dell
* @description 针对表【student_class】的数据库操作Service
* @createDate 2023-05-07 13:10:07
*/
public interface StudentClassService extends IService<StudentClass> {

    PageInfo<StudentClass> page(StudentClassPageRequestVM requestVM);

}
