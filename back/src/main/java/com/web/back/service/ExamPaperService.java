package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.ExamPaper;
import com.web.back.domain.Teacher;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.admin.exam.ExamPaperPageRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/21.
 * @DESC:
 */
public interface ExamPaperService extends BaseService<ExamPaper> {

    PageInfo<ExamPaper> page(ExamPaperPageRequestVM requestVM);

    PageInfo<ExamPaper> taskExamPage(ExamPaperPageRequestVM requestVM);
    ExamPaper savePaperFromVM(ExamPaperEditRequestVM examPaperEditRequestVM,Teacher teacher);

    ExamPaperEditRequestVM examPaperToVM(Integer id);


    Integer selectAllCount();

    List<Integer> selectMothCount();
}
