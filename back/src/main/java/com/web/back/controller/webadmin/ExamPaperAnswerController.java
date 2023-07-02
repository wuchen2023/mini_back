package com.web.back.controller.webadmin;

import com.github.pagehelper.PageInfo;
import com.web.back.base.BaseApiController;
import com.web.back.domain.ExamPaperAnswer;
import com.web.back.domain.Subject;
import com.web.back.domain.Teacher;
import com.web.back.service.ExamPaperAnswerService;
import com.web.back.service.SubjectService;
import com.web.back.service.TeacherService;
import com.web.back.state.RestResponse;
import com.web.back.utils.DateTimeUtil;
import com.web.back.utils.ExamUtil;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.paper.ExamPaperAnswerPageRequestVM;
import com.web.back.viewmodel.student.exampaper.ExamPaperAnswerPageResponseVM;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by hongdou
 * @date 2023/7/2.
 * @DESC:
 */

@Api("管理后台-试卷答题的Api")
@RestController("WebAdminExamPaperAnswerController")
@RequestMapping(value = "/api/webadmin/examPaperAnswer")
public class ExamPaperAnswerController extends BaseApiController {
    private final ExamPaperAnswerService examPaperAnswerService;

    private final SubjectService subjectService;

    private final TeacherService teacherService;

    @Autowired
    public ExamPaperAnswerController(ExamPaperAnswerService examPaperAnswerService, SubjectService subjectService, TeacherService teacherService){
        this.examPaperAnswerService = examPaperAnswerService;
        this.subjectService  = subjectService;
        this.teacherService = teacherService;
    }

    //返回页面
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public RestResponse<PageInfo<ExamPaperAnswerPageResponseVM>> pageJudgeList(@RequestBody ExamPaperAnswerPageRequestVM model){
        //判断答题情况
        PageInfo<ExamPaperAnswer> pageInfo = examPaperAnswerService.adminPage(model);
        PageInfo<ExamPaperAnswerPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperAnswerPageResponseVM vm = modelMapper.map(e, ExamPaperAnswerPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setDoTime(ExamUtil.secondToVM(e.getDoTime()));
            vm.setSystemScore(ExamUtil.scoreToVM(e.getSystemScore()));
            vm.setUserScore(ExamUtil.scoreToVM(e.getUserScore()));
            vm.setPaperScore(ExamUtil.scoreToVM(e.getPaperScore()));
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            Teacher teacher = teacherService.selectById(e.getCreateUser());
            vm.setUserName(teacher.getAccount());
            return vm;
        });
        return RestResponse.ok(page);
    }
}
