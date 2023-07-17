package com.web.back.mapper;

import com.web.back.domain.StudentGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【student_group】的数据库操作Mapper
* @createDate 2023-05-07 13:10:14
* @Entity generator.domain.StudentGroup
*/
@Mapper
public interface StudentGroupMapper extends BaseMapper<StudentGroup> {

    List<Integer> findAllStuGroupIds(Integer teacherGroupId);
}




