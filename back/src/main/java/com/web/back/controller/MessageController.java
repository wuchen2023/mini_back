package com.web.back.controller;

import com.web.back.domain.result.Cov;
import com.web.back.domain.result.Fridend;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.web.back.service.user_relationService;
import com.web.back.service.ConversationService;
import com.web.back.service.MessageService;
import com.web.back.domain.result.ReMessage;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api("聊天相关的Api")
public class MessageController {
    @Resource
    user_relationService user_relationService;


    @ResponseBody
    @PostMapping("add_fridend")
    @ApiOperation("添加好友")
    public ResposeResult add_friend(@RequestParam Integer userid, @RequestParam Integer friendid, @RequestParam Integer identity)
    {
        return user_relationService.add_friend(userid, friendid, identity);
    }

    @ResponseBody
    @PostMapping("delete_fridend")
    @ApiOperation("删除好友")
    public ResposeResult delete_friend(@RequestParam Integer userid, @RequestParam Integer friendid)
    {
        return user_relationService.delete_friend(userid, friendid);
    }

    @ResponseBody
    @PostMapping("get_fridend")
    @ApiOperation("获取好友列表")
    public List<Fridend> get_friend(@RequestParam Integer userid)
    {
        return user_relationService.get_friend(userid);
    }

    @Resource
    ConversationService conversationService;

    @ResponseBody
    @PostMapping("add_conversation")
    @ApiOperation("添加会话")
    public ResposeResult add_conversation(@RequestParam Integer userid, @RequestParam Integer chatid, @RequestParam Integer identity)
    {
        return conversationService.add_conversation(userid, chatid, identity);
    }

    @ResponseBody
    @PostMapping("get_conversation")
    @ApiOperation("获取会话列表")
    public List<Cov> get_conversation(@RequestParam Integer userid)
    {
        return conversationService.get_all_conversation(userid);
    }

    @Resource
    MessageService messageService;

    @ResponseBody
    @PostMapping("add_message")
    @ApiOperation("发送消息")
    public ResposeResult add_message(@RequestParam Integer sender_id, @RequestParam Integer receiver_id, @RequestParam String content, @RequestParam Integer identity, @RequestParam Integer identity_sender)
    {
        return messageService.add_message(sender_id, receiver_id, content, identity, identity_sender);
    }

    @ResponseBody
    @PostMapping("get_message")
    @ApiOperation("获取消息")
    public List<ReMessage> get_message(@RequestParam Integer sender_id, @RequestParam Integer receiver_id, @RequestParam Integer identity1, @RequestParam Integer identity2)
    {
        return messageService.get_message(sender_id, receiver_id, identity1, identity2);
    }

}
