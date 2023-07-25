package com.web.back.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public class UserEventLog implements Serializable {
    private static final long serialVersionUID = 5351759672842341094L;

    public UserEventLog(){

    }

    public UserEventLog(Integer userId, String userName, Date createTime) {
        this.userId = userId;
        this.userName = userName;
        this.createTime = createTime;
    }

    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
