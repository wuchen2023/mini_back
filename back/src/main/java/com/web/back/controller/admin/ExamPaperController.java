package com.web.back.controller.admin;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.ExamPaper;
import com.web.back.domain.Subject;
import com.web.back.service.ExamPaperAnswerService;
import com.web.back.service.ExamPaperService;
import com.web.back.service.SubjectService;
import com.web.back.state.RestResponse;
import com.web.back.utils.DateTimeUtil;
import com.web.back.utils.ModelMapperSingle;
import com.web.back.utils.PageInfoHelper;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageResponseVM;
import com.web.back.viewmodel.student.exam.ExamPaperPageVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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

    /**
     * 该功能是点击试卷进入答题这个过程，点击的时候会获取到该试卷的id，前端携带id进行查询，返回试卷的具体内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.POST)
    @ApiOperation("小程序端-依据id查询试卷（webadmin前缀的是后端平台接口）")
    public RestResponse<ExamPaperEditRequestVM> select(@PathVariable Integer id){
        ExamPaperEditRequestVM vm = examPaperService.examPaperToVM(id);
        return RestResponse.ok(vm);
    }

    /**
     * 对于pagelist，
     * 前端携带当前点击的课程名id(在获取班级列表的时候能够获取到），在点击进入课程的时候即发送请求，也就是说ExamPaperPageVM 中携带infoClassContentID的具体信息
     *这里不区分老师和学生，同一传入课程
     * @param model
     * @return
     */
    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ApiOperation("小程序端-获取试卷列表")
    public RestResponse<PageInfo<ExamPaperPageResponseVM>> pageList(@Valid ExamPaperPageVM model) {

//        model.setLevelId(getCurrentUser().getUserLevel()); //这里setlevelid是干什么的
        PageInfo<ExamPaper> pageInfo = examPaperService.examPage(model);  //这里就将请求的model带入进行查询了
        PageInfo<ExamPaperPageResponseVM> page = PageInfoHelper.copyMap(pageInfo, e -> {
            ExamPaperPageResponseVM vm = modelMapper.map(e, ExamPaperPageResponseVM.class);
            Subject subject = subjectService.selectById(vm.getSubjectId());
            vm.setSubjectName(subject.getName());
            vm.setCreateTime(DateTimeUtil.dateFormat(e.getCreateTime()));
            return vm;
        });
        return RestResponse.ok(page);
    }


}
