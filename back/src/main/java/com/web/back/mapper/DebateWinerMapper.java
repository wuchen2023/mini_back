package com.web.back.mapper;

import com.web.back.domain.Debate;
import com.web.back.domain.DebateWiner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.web.back.domain.result.DebateWinnerRes;
import com.web.back.domain.result.PkWinnerRes;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
//@Mapper
public interface DebateWinerMapper extends BaseMapper<DebateWiner> {

    public DebateWinnerRes get_winner(Integer activity_id, Integer winner_stu_group_id);

}
