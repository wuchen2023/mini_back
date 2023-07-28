package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/7/28.
 * @DESC:
 */
@TableName(value = "post_reply")
@Data
@AllArgsConstructor
public class PostReply implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer postId;

    private String replyContent;

    private String replyName;

    private Date createTime;

    public PostReply(Integer postId, String replyContent, String replyName) {
        this.postId = postId;
        this.replyContent = replyContent;
        this.replyName = replyName;
        this.createTime = new Date();
    }

    public PostReply() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent){
        this.replyContent = replyContent;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
