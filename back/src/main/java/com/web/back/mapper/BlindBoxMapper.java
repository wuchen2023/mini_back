package com.web.back.mapper;

import com.web.back.domain.BlindBox;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.DebateVote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author Dell
* @description 针对表【blind_box】的数据库操作Mapper
* @createDate 2023-05-07 13:09:42
* @Entity generator.domain.BlindBox
*/
@Mapper
public interface BlindBoxMapper extends BaseMapper<BlindBox> {

    List<BlindBox> findInfo(String stu_account,Integer exam_paper_id);
    List<BlindBox> findInfo_condition(String class_name,String stu_account,String teacher_account);

    List<BlindBox> view_result(String stuAccount);

}




