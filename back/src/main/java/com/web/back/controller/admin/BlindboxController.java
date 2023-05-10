package com.web.back.controller.admin;

import com.web.back.service.QuestionService;
import com.web.back.service.TextContentService;
import com.web.back.state.RestResponse;
import com.web.back.viewmodel.admin.question.QuestionEditRequestVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
@Api("盲盒的Api")
@RestController
@RequestMapping(value = "/api/admin/blindbox")
public class BlindboxController {
    /**
     * 随机抽取一道题目并返回
     */
    private final QuestionService questionService;

    private final TextContentService textContentService;

    @Autowired
    public BlindboxController(QuestionService questionService, TextContentService textContentService){
        this.questionService = questionService;
        this.textContentService = textContentService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 传入老师标识码和账户验证老师登录，然后调用盲盒接口开始抽题，
     * @param code
     * @param teacher_account
     * @return
     */
    @ResponseBody
    @ApiOperation("随机抽一道题")
    @PostMapping("blindbox")
    public RestResponse<QuestionEditRequestVM> blindbox(@RequestParam String code,@RequestParam String teacher_account){
        if (code.equals(redis_get(teacher_account))){
            System.out.println("查询的结果是："+questionService.selectAllCount());
            if(questionService.selectAllCount()>0){
                int idd = getRandomId(10);
                QuestionEditRequestVM newVM = questionService.getQuestionEditRequestVM(idd);
                return RestResponse.ok(newVM);
            }
            else{
                return RestResponse.fail(400, "题目数量为0!!!");
            }
        }else{
            return RestResponse.fail(400,"未登录，请登录！");
        }

    }

    //按照传入的题目列表大小随机在这写数中选择一个id
    public static int getRandomId(int id) {
        Random random = new Random();
        int MAX = id, MIN = 4;
        int number = random.nextInt(MAX - MIN + 1) + MIN;
        System.out.println("随机生成的数字是："+number);
        return number;
    }
    public void redis_save(String key, String value) {
        redisTemplate.opsForValue().set(key + "-teacher", value, 7, TimeUnit.DAYS);
    }
    public String redis_get(String key) {
        return (String) redisTemplate.opsForValue().get(key + "-teacher");
    }

    /**
     * 抽题完成后在前端做题，做完题目后要记录做题的记录
     */


}
