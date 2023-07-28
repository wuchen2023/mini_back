package com.web.back.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.web.back.domain.post.tag;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    public Post(String title, String content,String account,String className) {
        this.title = title;
        this.content = content;
        this.createUser = account;
        this.className = className;
        this.createTime = new Date();

    }

    private String createUser; //由于学生和老师都可以创建，因此存储的不是对应id而是直接存储用户名

    private Date createTime;

    private String className;

    private Integer replyCount;

    public Post(Integer id, String title,String content) {
        this.id = id;
        this.title = title;
        this.content = content;

    }

//    @ManyToMany
//    @JoinTable(
//            name = "post_tag",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id"))
//    private Set<tag> tags = new HashSet<>();

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

//    public Set<tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(Set<tag> tags) {
//        this.tags = tags;
//    }

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
