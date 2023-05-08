package com.web.back.controller;

import com.web.back.service.QuestionService;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
@Controller
@Api("问题的Api")
public class QuestionController {
    @Resource
    QuestionService questionService;

    @ResponseBody
    @GetMapping("get_question")
    @ApiOperation("查询quetion")
    public ResposeResult get_question(@RequestParam Integer id){
        ResposeResult re = questionService.get_question(id);

        if (re.getCode()==0){
            return new ResposeResult(0,"查询失败");
        }else if(re.getCode()==1){
            return re;
        }
        else{
            return re;
        }
    }
}
