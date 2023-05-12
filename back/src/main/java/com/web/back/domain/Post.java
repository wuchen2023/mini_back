package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
@TableName(value = "post")
@Data
@AllArgsConstructor
public class Post implements Serializable {
//    设置自增
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    private Boolean deleted;
    public Post(String title, String content) {
        this.title = title;
        this.content = content;

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
