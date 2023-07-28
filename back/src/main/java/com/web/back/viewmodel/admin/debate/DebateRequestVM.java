package com.web.back.viewmodel.admin.debate;

import com.web.back.viewmodel.admin.BaseVM;
import io.swagger.models.auth.In;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/12.
 * @DESC:
 */
public class DebateRequestVM extends BaseVM {
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Boolean deleted;

    private String createUser;

    private Date createTime;

    private String className;

    private Integer replyCount;



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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getClassName(){
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getReplyCount(){
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }
}
