package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.Question;
import com.web.back.state.ResposeResult;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public interface QuestionService extends IService<Question> {
    public ResposeResult get_question(Integer id);
}
