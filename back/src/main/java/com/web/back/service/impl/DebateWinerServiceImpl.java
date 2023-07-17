package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.DebateWiner;
import com.web.back.domain.result.DebateWinnerRes;
import com.web.back.mapper.DebateWinerMapper;
import com.web.back.service.DebateWinerService;
import com.web.back.state.ResposeResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
@Service
public class DebateWinerServiceImpl extends ServiceImpl<DebateWinerMapper, DebateWiner>
implements DebateWinerService{
    @Resource
    DebateWinerMapper debateWinerMapper;

    @Override
    public ResposeResult add_debate_winner(Integer student_group_id, Integer activity_id){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("student_group_id", student_group_id);
            queryWrapper.eq("activity_id", activity_id);

            DebateWiner debateWiner = debateWinerMapper.selectOne(queryWrapper);
            if(debateWiner != null)
            {
                throw new Exception();
            }

            DebateWiner debateWiner1 = new DebateWiner(student_group_id, activity_id);
            debateWinerMapper.insert(debateWiner1);
            return new ResposeResult(1, "添加辩论会winner组成功");
        }catch (Exception e){
            return new ResposeResult(0, "添加辩论会winner组失败");
        }
    }

    @Override
    public DebateWinnerRes get_winner(Integer activity_id, Integer winner_stu_group_id){
        try{
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("activity_id",activity_id);
            DebateWiner debateWiner = debateWinerMapper.selectOne(queryWrapper);
            if(debateWiner == null){
                throw new Exception();
            }
            DebateWinnerRes debateWinnerRes = debateWinerMapper.get_winner(activity_id, winner_stu_group_id);
            return debateWinnerRes;

        }catch (Exception e){
            return null;
        }
    }



}
