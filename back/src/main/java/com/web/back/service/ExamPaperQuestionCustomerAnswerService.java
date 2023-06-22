package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.ExamPaperQuestionCustomerAnswer;
import com.web.back.domain.other.ExamPaperAnswerUpdate;
import com.web.back.viewmodel.student.exam.ExamPaperSubmitItemVM;
import com.web.back.viewmodel.student.question.answer.QuestionPageStudentRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public interface ExamPaperQuestionCustomerAnswerService extends BaseService<ExamPaperQuestionCustomerAnswer>{
    PageInfo<ExamPaperQuestionCustomerAnswer> studentPage(QuestionPageStudentRequestVM requestVM);

    List<ExamPaperQuestionCustomerAnswer> selectListByPaperAnswerId(Integer id);

    /**
     * 试卷提交答案入库
     *
     * @param examPaperQuestionCustomerAnswers List<ExamPaperQuestionCustomerAnswer>
     */
    void insertList(List<ExamPaperQuestionCustomerAnswer> examPaperQuestionCustomerAnswers);

    /**
     * 试卷问题答题信息转成ViewModel 传给前台
     *
     * @param qa ExamPaperQuestionCustomerAnswer
     * @return ExamPaperSubmitItemVM
     */
    ExamPaperSubmitItemVM examPaperQuestionCustomerAnswerToVM(ExamPaperQuestionCustomerAnswer qa);


    Integer selectAllCount();

    List<Integer> selectMothCount();

    int updateScore(List<ExamPaperAnswerUpdate> examPaperAnswerUpdates);
}
