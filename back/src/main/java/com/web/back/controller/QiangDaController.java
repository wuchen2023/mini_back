package com.web.back.controller;


import com.web.back.domain.Qiangda;
import com.web.back.service.QiangdaService;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("抢答相关Api")
public class QiangDaController {


    @Resource
    QiangdaService qiangdaService;


    @ResponseBody
    @ApiOperation("添加抢答")
    @PostMapping("add_qiangda")
    public ResposeResult add_qiangda(@RequestParam Integer teacher_id, @RequestParam String qiangda_type, @RequestParam String course_name, @RequestParam String question, @RequestParam String reference_answer)
    {
        Qiangda qiangda = new Qiangda(teacher_id, qiangda_type, course_name, 0, question, reference_answer);
        return qiangdaService.add_qiangda(qiangda);
    }

    @ResponseBody
    @ApiOperation("获取当前班级的所有抢答")
    @PostMapping("get_all_qiangda")
    public List<Qiangda> get_all_qiangda(@RequestParam Integer teacher_id, @RequestParam String course_name)
    {
//        return qiangdaService.get_all_qiangda(teacher_id, course_name);
        return qiangdaService.get_all_qiangda1(teacher_id, course_name);
    }

    @ResponseBody
    @ApiOperation("获取抢答的状态")
    @PostMapping("get_qiangda_state")
    public ResposeResult get_qiangda_state(@RequestParam Integer qiandga_id)
    {
        return qiangdaService.get_qiangda_state(qiandga_id);
    }

    @ResponseBody
    @ApiOperation("关闭抢答")
    @PostMapping("close_qiangda")
    public ResposeResult close_qiangda(@RequestParam Integer qiangda_id)
    {
        return qiangdaService.close_qiangda(qiangda_id);
    }


    @ResponseBody
    @ApiOperation("老师判断抢答是否正确")
    @PostMapping("qiangda_is_right")
    public ResposeResult qiangda_is_right(@RequestParam Integer qiangda_id, @RequestParam  String is_right){
        return qiangdaService.qiangda_is_right(qiangda_id, is_right);
    }

}
