package com.web.back.mapper;

import com.web.back.domain.PostReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
@Mapper
public interface PostReplyMapper extends BaseMapper<PostReply> {

    Integer delete_post_reply(Integer id);

    Integer delete_post_reply_by_post_id(Integer postId);

}
