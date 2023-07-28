package com.web.back.viewmodel.admin.post;

import com.web.back.domain.PostReply;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
public class PostWithRepliesVM {
    Integer postId;

    String postTitle;

    String postContent;
    String postCreateUser;
    Date postCreateTime;
    String postClassName;
    Integer postReplyCount;
    private List<PostReply> replies;

    Integer replyId;
    String replyContent;
    String replyName;
    public PostWithRepliesVM(Integer postId, String postTitle, String postContent, String postCreateUser, Date postCreateTime, String postClassName, Integer postReplyCount, Integer replyId, String replyContent, String replyName) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postCreateUser = postCreateUser;
        this.postCreateTime = postCreateTime;
        this.postClassName = postClassName;
        this.postReplyCount = postReplyCount;

        // Create a new PostReply object and set its properties
        PostReply reply = new PostReply();
        reply.setId(replyId);
        reply.setReplyContent(replyContent);
        reply.setReplyName(replyName);

        // Add the reply object to the replies collection
        this.replies = new ArrayList<>();
        this.replies.add(reply);
//        this.replies.add(reply);
    }
}
