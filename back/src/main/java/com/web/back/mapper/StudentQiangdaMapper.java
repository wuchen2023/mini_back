package com.web.back.mapper;

import com.web.back.domain.StudentQiangda;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.StudentQiangDaRes;

/**
* @author Dell
* @description 针对表【student_qiangda】的数据库操作Mapper
* @createDate 2023-07-16 12:10:05
* @Entity com.web.back.domain.StudentQiangda
*/
public interface StudentQiangdaMapper extends BaseMapper<StudentQiangda> {

    StudentQiangDaRes get_qiangda_res(Integer qiangda_id, String course_name);

}




