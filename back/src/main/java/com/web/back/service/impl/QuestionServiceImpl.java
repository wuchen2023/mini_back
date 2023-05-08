package com.web.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.Question;
import com.web.back.mapper.QuestionMapper;
import com.web.back.service.QuestionService;
import com.web.back.state.ResposeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    @Resource
    QuestionMapper questionMapper;


    @Override
    public ResposeResult get_question(Integer id){

            Question question = questionMapper.selectById(id);

            log.info(question.getId() + "查询到此id的题目");
            return new ResposeResult(1,"查询成功");
    }
}
