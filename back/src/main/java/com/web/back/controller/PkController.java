package com.web.back.controller;


import com.web.back.domain.ExamPaper;
import com.web.back.domain.Pk;
import com.web.back.domain.result.PkRes;
import com.web.back.domain.result.PkWinnerRes;
import com.web.back.service.ExamPaperService;
import com.web.back.service.PkService;
import com.web.back.service.PkWinerService;
import com.web.back.service.QuestionService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import com.web.back.viewmodel.admin.exam.ExamPaperEditRequestVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("Pk相关接口")
public class PkController {

    @Resource
    PkService pkService;

    @Resource
    ExamPaperService examPaperService;
//
//    @ResponseBody
//    @ApiOperation("创建相关activity的pk")
//    @PostMapping("add_pk")
//    public ResposeResult add_pk(@RequestParam Integer activity_id, @RequestParam String course_name)
//    {
//        return pkService.add_pk(activity_id, course_name);
//
//    }

    @ResponseBody
    @ApiOperation("创建相关activity的pk")
    @PostMapping("add_pk")
    public List<Integer> add_pk(@RequestParam Integer activity_id, @RequestParam String course_name)
    {
//        return pkService.add_pk(activity_id, course_name);
//        ExamPaper examPaper = pkService.add_pk(activity_id, course_name);
//        ExamPaperEditRequestVM newVM = examPaperService.examPaperToVM(examPaper.getId());
//        return RestResponse.ok(newVM);
        return pkService.add_pk(activity_id, course_name);

    }

//    @ResponseBody
//    @ApiOperation("关闭一个activity的pk")
//    @PostMapping("close_pk")
//    public ResposeResult close_pk(@RequestParam Integer activity_id, @RequestParam String course_name, @RequestParam Integer exam_paper_id)
//    {
//        ExamPaper examPaper = examPaperService.selectById(exam_paper_id);
//        examPaper.setDeleted(true);
//        examPaperService.updateByIdFilter(examPaper);
//        return pkService.close_pk(activity_id, course_name);
//    }

    @ResponseBody
    @ApiOperation("关闭一个activity的pk")
    @PostMapping("close_pk")
    public ResposeResult close_pk(@RequestParam Integer activity_id, @RequestParam String course_name, @RequestParam("items") List<String> items)
    {
        System.out.println("传入的试卷id为："+items);
        ExamPaper examPaper1 = examPaperService.selectById(Integer.valueOf(items.get(0)));
        examPaper1.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper1);
        ExamPaper examPaper2 = examPaperService.selectById(Integer.valueOf(items.get(1)));
        examPaper2.setDeleted(true);
        examPaperService.updateByIdFilter(examPaper2);
        return pkService.close_pk(activity_id, course_name);
    }


    @ResponseBody
    @ApiOperation("获取一个pk的结束状态")
    @PostMapping("get_pk_state")
    public ResposeResult get_pk_state(@RequestParam Integer activity_id, @RequestParam String course_name)
    {
        return pkService.get_pk_state(activity_id, course_name);
    }

    @ResponseBody
    @ApiOperation("获取当前班级的所有pk")
    @PostMapping("get_all_pk")
    public List<PkRes> get_all_pk(@RequestParam String course_name)
    {
        return pkService.get_all_pk(course_name);
    }

    @Resource
    PkWinerService pkWinerService;

    @ResponseBody
    @ApiOperation("添加pk胜者")
    @PostMapping("add_pk_winner")
    public ResposeResult add_pk_winner(@RequestParam Integer student_id, @RequestParam Integer activity_id)
    {
        return pkWinerService.add_pk_winner(student_id, activity_id);
    }

    @ResponseBody
    @ApiOperation("获取胜者信息")
    @PostMapping("get_winner")
    public PkWinnerRes get_winner(@RequestParam Integer activity_id)
    {
        return pkWinerService.get_winner(activity_id);
    }



}
