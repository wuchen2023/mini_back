package com.web.back.controller.admin;

import com.web.back.domain.PostReply;
import com.web.back.service.PostReplyService;
import com.web.back.state.ResposeResult;
import com.web.back.state.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
@Controller
@Api("PostReply的API")
@RequestMapping(value = "api/admin/post_reply")
public class PostReplyController {
    @Resource
    PostReplyService postReplyService;

    @ResponseBody
    @ApiOperation("老师添加回复，回复某条帖子")
    @PostMapping("/teacher/add_reply")
    public ResposeResult tea_add_post_reply(@RequestParam Integer postId, @RequestParam String replyContent,@RequestParam String teacher_account){
        PostReply postReply = new PostReply(postId,replyContent, teacher_account);
        return postReplyService.add_reply(postReply);
    }

    @ResponseBody
    @ApiOperation("学生添加回复，回复某条帖子")
    @PostMapping("/student/add_reply")
    public ResposeResult stu_add_post_reply(@RequestParam Integer postId, @RequestParam String replyContent,@RequestParam String student_account){
        PostReply postReply = new PostReply(postId,replyContent, student_account);
        return postReplyService.add_reply(postReply);
    }

    @ResponseBody
    @ApiOperation("删除某条回复")
    @PostMapping("/delete_reply")
    public ResposeResult delete_reply(@RequestParam Integer id){
        return  postReplyService.delete_reply(id);
    }
}
