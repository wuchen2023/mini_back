package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.DebateWiner;
import com.web.back.domain.result.DebateWinnerRes;
import com.web.back.state.ResposeResult;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public interface DebateWinerService extends IService<DebateWiner> {

    public ResposeResult add_debate_winner(Integer student_group_id, Integer activity_id);

    DebateWinnerRes get_winner(Integer activity_id, Integer winner_stu_group_id);
}
