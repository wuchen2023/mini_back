package com.web.back.service;

import com.web.back.domain.Conversation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.Cov;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
* @author Dell
* @description 针对表【conversation】的数据库操作Service
* @createDate 2023-06-19 01:52:19
*/
public interface ConversationService extends IService<Conversation> {

    public ResposeResult add_conversation(Integer userId, Integer chatId, Integer identity);

    public List<Cov> get_all_conversation(Integer userId);

}
