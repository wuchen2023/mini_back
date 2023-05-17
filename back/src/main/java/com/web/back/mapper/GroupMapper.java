package com.web.back.mapper;

import com.web.back.domain.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author Dell
* @description 针对表【group】的数据库操作Mapper
* @createDate 2023-05-07 13:09:55
* @Entity generator.domain.Group
*/
@Mapper
public interface GroupMapper extends BaseMapper<Group> {

    @Select("SELECT * from `group` where group_type=#{group_type}")
    Group get_one(String group_type);

    @Select("SELECT * from `group` where id=#{group_id}")
    Group get_one_by_group_id(Integer group_id);

    @Insert("INSERT into `group`(group_size, group_type) values(#{group_size}, #{group_type})")
    void insert_one(Integer group_size, String group_type);


}




