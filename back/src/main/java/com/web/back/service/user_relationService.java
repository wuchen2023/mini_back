package com.web.back.service;

import com.web.back.domain.result.Fridend;
import com.web.back.domain.user_relation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
* @author Dell
* @description 针对表【user_relation】的数据库操作Service
* @createDate 2023-06-19 00:43:43
*/
public interface user_relationService extends IService<user_relation> {
    ResposeResult add_friend(Integer userId, Integer friendId, Integer identity);
    ResposeResult delete_friend(Integer userId, Integer friendId);

    List<Fridend> get_friend(Integer userId);
}
