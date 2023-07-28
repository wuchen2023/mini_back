package com.web.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.back.domain.ExamPaper;
import com.web.back.domain.Post;
import com.web.back.domain.PostReply;
import com.web.back.mapper.PostMapper;
import com.web.back.mapper.PostReplyMapper;
import com.web.back.service.PostReplyService;
import com.web.back.state.ResposeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
@Service
@Slf4j
public class PostReplyServiceImpl extends ServiceImpl<PostReplyMapper, PostReply> implements PostReplyService {
    @Resource
    PostReplyService postReplyService;

    @Resource
    PostReplyMapper postReplyMapper;
    @Resource
    PostMapper postMapper;

    @Override
    public ResposeResult add_reply(PostReply postReply) {
        try {
            postReplyMapper.insert(postReply);
            postMapper.update_post_reply_count(postReply.getPostId());
            log.info("新建帖子回复成功，id为：" + postReply.getId());
        } catch (Exception e) {
            return new ResposeResult(0, "新增失败");
        }
        String post_reply_id = String.valueOf(postReply.getId());
        return new ResposeResult(1, post_reply_id);
    }

    @Override
    public ResposeResult delete_reply(Integer id) {

        try{
            //在删除之前先查询
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            PostReply postReply = postReplyMapper.selectOne(queryWrapper);
            //然后删除
            Integer status = postReplyMapper.delete_post_reply(id);
            System.out.println(status);
            if(status==0){
                throw  new Exception();
            }
           //然后更新
            postMapper.update_post_reply_count(postReply.getPostId());

        }catch (Exception e){
            return new ResposeResult(0,"删除失败");
        }

        return new ResposeResult(1, "删除成功");
    }

    @Override
    public Integer delete_post_reply_by_post_id(Integer postId) {
        return postReplyMapper.delete_post_reply_by_post_id(postId);
    }
}
