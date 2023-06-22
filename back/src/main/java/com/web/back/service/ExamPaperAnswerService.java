package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.ExamPaperAnswer;
import com.web.back.domain.ExamPaperAnswerInfo;
import com.web.back.domain.Teacher;
import com.web.back.viewmodel.student.exam.ExamPaperSubmitVM;
import com.web.back.viewmodel.student.exampaper.ExamPaperAnswerPageVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
public interface ExamPaperAnswerService extends BaseService<ExamPaperAnswer> {

    /**
     * 学生考试记录分页
     *
     * @param requestVM 过滤条件
     * @return PageInfo<ExamPaperAnswer>
     */
    PageInfo<ExamPaperAnswer> studentPage(ExamPaperAnswerPageVM requestVM);

    /**
     * 计算试卷提交结果(不入库)
     *
     * @param examPaperSubmitVM
     * @param teacher
     * @return
     */
    ExamPaperAnswerInfo calculateExamPaperAnswer(ExamPaperSubmitVM examPaperSubmitVM, Teacher teacher);


    /**
     * 试卷批改
     * @param examPaperSubmitVM  examPaperSubmitVM
     * @return String
     */
    String judge(ExamPaperSubmitVM examPaperSubmitVM);

    /**
     * 试卷答题信息转成ViewModel 传给前台
     *
     * @param id 试卷id
     * @return ExamPaperSubmitVM
     */
    ExamPaperSubmitVM examPaperAnswerToVM(Integer id);


    Integer selectAllCount();

    List<Integer> selectMothCount();

    PageInfo<ExamPaperAnswer> adminPage(com.web.back.viewmodel.admin.paper.ExamPaperAnswerPageRequestVM requestVM);
}
