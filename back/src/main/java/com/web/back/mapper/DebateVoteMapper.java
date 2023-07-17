package com.web.back.mapper;

import com.web.back.domain.DebateVote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
@Mapper
public interface DebateVoteMapper extends BaseMapper<DebateVote> {

    List<DebateVote> findInfo(Integer debate_id);
}
