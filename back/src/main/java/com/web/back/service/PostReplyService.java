package com.web.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.back.domain.PostReply;
import com.web.back.state.ResposeResult;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
public interface PostReplyService extends IService<PostReply> {
    public ResposeResult add_reply(PostReply postReply);

    public ResposeResult delete_reply(Integer id);

    public Integer delete_post_reply_by_post_id(Integer postId);

}
