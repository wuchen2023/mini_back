package com.web.back.controller.admin;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.ExamPaper;
import com.web.back.domain.Subject;
import com.web.back.service.ExamPaperAnswerService;
import com.web.back.service.ExamPaperService;
import com.web.back.service.SubjectService;
import com.web.back.state.RestResponse;
import com.web.back.utils.DateTimeUtil;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageResponseVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author by hongdou
 * @date 2023/7/2.
 * @DESC:
 */
@Api("固定试卷的APi")
@RestController
@RequestMapping(value = "/api/admin/student/exampaper")
public class ExamPaperController{
    private final ExamPaperService examPaperService;

    private final SubjectService subjectService;

    @Autowired
    public ExamPaperController(ExamPaperService examPaperService,SubjectService subjectService){
        this.examPaperService = examPaperService;

        this.subjectService = subjectService;
    }


    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id){
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

//    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
//    public RestResponse<PageInfo<ExamPaperPageResponseVM>> pageList(@Valid ExamPaperPageVM model) {
//        model.setLevelId(getCurrentUser().getUserLevel()); //这里setlevelid是干什么的
//        PageInfo<ExamPaper> pageInfo = examPaperService.studentPage(model);
//        PageInfo<ExamPaperPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
//            ExamPaperPageResponseVM vm = modelMapper.map(e, ExamPaperPageResponseVM.class);
//            Subject subject = subjectService.selectById(vm.getSubjectId());
//            vm.setSubjectName(subject.getName());
//            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
//            return vm;
//        });
//        return RestResponse.ok(page);
//    }
}
