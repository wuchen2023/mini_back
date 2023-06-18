package com.web.back.controller;

import com.web.back.domain.result.Fridend;
import com.web.back.state.ResposeResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.web.back.service.user_relationService;

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
}
