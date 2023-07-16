package com.web.back.service;

import com.web.back.domain.StudentQiangda;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.StudentQiangDaRes;
import com.web.back.state.ResposeResult;

/**
* @author Dell
* @description 针对表【student_qiangda】的数据库操作Service
* @createDate 2023-07-16 12:10:05
*/
public interface StudentQiangdaService extends IService<StudentQiangda> {

    public ResposeResult add_student_qiangda(StudentQiangda studentQiangda);

    public StudentQiangDaRes get_qiangda_res(Integer qiangda_id, String course_name);

}
