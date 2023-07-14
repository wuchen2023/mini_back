package com.web.back.mapper;

import com.web.back.domain.Pk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.PkRes;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Dell
* @description 针对表【pk】的数据库操作Mapper
* @createDate 2023-05-07 13:10:00
* @Entity generator.domain.Pk
*/
public interface PkMapper extends BaseMapper<Pk> {

    List<PkRes> get_all(String course_name);

}




