package com.web.back.mapper;

import com.web.back.domain.Qiangda;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【qiangda】的数据库操作Mapper
* @createDate 2023-07-16 11:38:22
* @Entity com.web.back.domain.Qiangda
*/
@Mapper
public interface QiangdaMapper extends BaseMapper<Qiangda> {
    List<Qiangda> find_info(String course_name, Integer teacher_id);
}




