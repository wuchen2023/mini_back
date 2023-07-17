package com.web.back.service;

import com.web.back.domain.Debate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.Grouping;
import com.web.back.state.ResposeResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author Dell
* @description 针对表【debate】的数据库操作Service
* @createDate 2023-05-07 13:09:49
*/
public interface DebateService extends IService<Debate> {

    public List<Object> add_debateTopic(Integer activityId, Integer teacher_id, String course_name, String debateTopic, Integer group_id);

    public ResposeResult close_debate(Integer debate_id);

    public ResposeResult get_debate_state(Integer debate_id);

    public List<Debate> get_all_debate(String course_name);

}
