package com.web.back.mapper;

import com.web.back.domain.Debate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【debate】的数据库操作Mapper
* @createDate 2023-05-07 13:09:49
* @Entity generator.domain.Debate
*/
public interface DebateMapper extends BaseMapper<Debate> {

    List<Debate> select_by_coursename(String course_name);
}




