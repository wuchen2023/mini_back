package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.DebateVote;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.debateVote.DebateVoteInfoVM;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
public interface DebateVoteService extends IService<DebateVote> {

    public ResposeResult add_ticket(Integer debate_id,Integer stu_group_id);
    public DebateVoteInfoVM get_debate_info(Integer debate_id);
}
