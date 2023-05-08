package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.Question;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import com.web.back.viewmodel.admin.question.QuestionPageRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
/**
 * 以下是测试
 */
//public interface QuestionService extends IService<Question> {
//    public ResposeResult get_question(Integer id);
//}
public interface QuestionService extends BaseService<Question>{
    PageInfo<Question> page(QuestionPageRequestVM requestVM);


    Question insertFullQuestion(QuestionEditRequestVM model, Integer teacherID);

    Question updateFullQuestion(QuestionEditRequestVM model);

    QuestionEditRequestVM getQuestionEditRequestVM(Integer questionId);

    QuestionEditRequestVM getQuestionEditRequestVM(Question question);

    Integer selectAllCount();

    List<Integer> selectMothCount();
}