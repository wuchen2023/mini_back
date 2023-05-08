package com.web.back.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 * @TableName:lib_text_content
 */
public class TextContent implements Serializable {
    private static final long serialVersionUID = -3359310751527928388L;

    /**
     * 内容
     */
    public TextContent(){

    }

    public TextContent(String content, Date createTime){
        this.content = content;
        this.createTime = createTime;
    }

    /**
     * 内容
     */
    private Integer id;
    private String content;
    /**
     * 创建时间
     */

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
