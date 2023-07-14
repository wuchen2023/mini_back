package com.web.back.mapper;

import com.web.back.domain.PkWiner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.back.domain.result.PkWinnerRes;
import org.apache.ibatis.annotations.Select;

/**
* @author Dell
* @description 针对表【pk_winer】的数据库操作Mapper
* @createDate 2023-07-14 16:41:02
* @Entity com.web.back.domain.PkWiner
*/
public interface PkWinerMapper extends BaseMapper<PkWiner> {


    public PkWinnerRes get_winner(Integer activity_id);

}




