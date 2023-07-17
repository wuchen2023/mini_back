package com.web.back.controller.admin;

import com.sun.istack.Interned;
import com.web.back.domain.Debate;
import com.web.back.domain.Grouping;
import com.web.back.domain.result.DebateWinnerRes;
import com.web.back.domain.result.PkWinnerRes;
import com.web.back.service.DebateService;
import com.web.back.service.DebateVoteService;
import com.web.back.service.DebateWinerService;
import com.web.back.state.ResposeResult;
import com.web.back.viewmodel.admin.debateVote.DebateVoteInfoVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.Incubating;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/7/17.
 * @DESC:
 */
@RestController
@Api("Debate相关接口")
public class DebateController {

    @Resource
    DebateService debateService;
    @Resource
    DebateWinerService debateWinerService;

    @Resource
    DebateVoteService debateVoteService;

    @ResponseBody
    @ApiOperation("创建相关activity的debate")
    @PostMapping("add_debate")
    public List<Object> add_debate(@RequestParam Integer activity_id, @RequestParam Integer teacher_id, @RequestParam String course_name, @RequestParam String debateTopic, @RequestParam Integer group_id)
    {

        return debateService.add_debateTopic(activity_id, teacher_id, course_name, debateTopic, group_id);

    }

    @ResponseBody
    @ApiOperation("关闭一个activity的debate")
    @PostMapping("close_debate")
    public ResposeResult close_debate(@RequestParam Integer debate_id){
        return debateService.close_debate(debate_id);
    }

    @ResponseBody
    @ApiOperation("获取一个debate的结束状态")
    @PostMapping("get_debate_state")
    public ResposeResult get_debate_state(@RequestParam Integer debate_id){

        return debateService.get_debate_state(debate_id);
    }

    @ResponseBody
    @ApiOperation("获取当前班级的所有debate")
    @PostMapping("get_all_debate")
    public List<Debate> get_all_debate(@RequestParam String course_name){
        return debateService.get_all_debate(course_name);
    }

    @ResponseBody
    @ApiOperation("添加debate胜利的那一组")
    @PostMapping("add_debate_winner")
    public ResposeResult add_pk_winner(@RequestParam Integer student_group_id, @RequestParam Integer activity_id){
        return  debateWinerService.add_debate_winner(student_group_id, activity_id);
    }


    @ResponseBody
    @ApiOperation("获取辩论会胜者信息")
    @PostMapping("get_debate_winner_info")
    public DebateWinnerRes get_debate_winner_info(@RequestParam Integer activity_id, @RequestParam Integer winner_stu_group_id)
    {
        return debateWinerService.get_winner(activity_id,winner_stu_group_id);
    }


    @ResponseBody
    @ApiOperation("学生给两组辩论会投票的接口，（每个学生只能投一组，当一个学生投了一票后，前端注意对投票按钮进行限制不能再投票）")
    @PostMapping("vote_stu_group")
    public ResposeResult vote_stu_group(@RequestParam Integer debate_id, @RequestParam Integer stu_group_id){
        return debateVoteService.add_ticket(debate_id, stu_group_id);
    }

    @ResponseBody
    @ApiOperation("查询某一次辩论会的两组投票数量")
    @PostMapping("select_debate_ticket")
    public DebateVoteInfoVM select_debate_ticket(@RequestParam Integer debate_id){
        return debateVoteService.get_debate_info(debate_id);
    }
}
