package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.DebateVote;
import com.web.back.mapper.DebateVoteMapper;
import com.web.back.service.DebateVoteService;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.debateVote.DebateVoteInfoVM;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
@Service
public class DebateVoteServiceImpl extends ServiceImpl<DebateVoteMapper, DebateVote> implements DebateVoteService {

    @Resource
    DebateVoteMapper debateVoteMapper;

    @Override
    public ResposeResult add_ticket(Integer debate_id, Integer stu_group_id) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("debate_id", debate_id);
            queryWrapper.eq("stu_group_id", stu_group_id);
            DebateVote debateVote = debateVoteMapper.selectOne(queryWrapper);
            if (debateVote != null) {
                Integer ticket = debateVote.getTickets();
                System.out.println("查询到的票数为：" + ticket);
                ticket++;
                QueryWrapper queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("debate_id", debate_id);
                queryWrapper1.eq("stu_group_id", stu_group_id);

                DebateVote debateVote1 = new DebateVote(debate_id, stu_group_id, ticket);
                debateVoteMapper.update(debateVote1, queryWrapper1);
                System.out.println("票数已经更新");
                return new ResposeResult(1, "票数已更新");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DebateVoteInfoVM get_debate_info(Integer debate_id){
        List<DebateVote> debateVoteList = new ArrayList<>();
        debateVoteList = debateVoteMapper.findInfo(debate_id);
        System.out.println("查询到的数据为："+debateVoteList);
        DebateVoteInfoVM debateVoteInfoVM = new DebateVoteInfoVM();
        debateVoteInfoVM.setDebate_id(debate_id);
        debateVoteInfoVM.setPositive_side_student_group_id(debateVoteList.get(0).getStu_group_id());
        debateVoteInfoVM.setPositive_side_tickets(debateVoteList.get(0).getTickets());
        debateVoteInfoVM.setNegative_side_student_group_id(debateVoteList.get(1).getStu_group_id());
        debateVoteInfoVM.setNegative_side_tickets(debateVoteList.get(1).getTickets());

        return debateVoteInfoVM;
    }

}
