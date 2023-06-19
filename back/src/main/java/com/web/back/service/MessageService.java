package com.web.back.service;

import com.web.back.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.result.ReMessage;
import com.web.back.state.ResposeResult;

import java.util.List;

/**
* @author Dell
* @description 针对表【message】的数据库操作Service
* @createDate 2023-06-19 02:11:36
*/
public interface MessageService extends IService<Message> {

    public ResposeResult add_message(Integer sender_id, Integer receiver_id, String content, Integer identity, Integer identity_sender);

    public List<ReMessage> get_message(Integer sender_id, Integer receiver_id, Integer identity1, Integer identity2);

}
