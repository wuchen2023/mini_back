package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.QuestionAnswer;
import com.web.back.domain.other.QuestionAnswerUpdate;
import com.web.back.viewmodel.student.question.QuestionAnswerPageRequestVM;
import com.web.back.viewmodel.student.question.QuestionSubmitItemVM;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
public interface QuestionAnswerService extends BaseService<QuestionAnswer> {

    PageInfo<QuestionAnswer> answerHistoryPage(QuestionAnswerPageRequestVM requestVM);

    List<QuestionAnswer> selectListByQuestionId(Integer id);

    /**
     * 试卷提交答案入库
     */
    void insertList(List<QuestionAnswer> questionAnswers);

    /**
     * 问题的答题信息转成viewmodel传给前台
     */
    QuestionSubmitItemVM quesionAnswerToVM(QuestionAnswer qa);

    Integer selectAllCount();

    List<Integer> selectMothCount();

    int updateScore(List<QuestionAnswerUpdate> questionAnswerUpdates);
}
