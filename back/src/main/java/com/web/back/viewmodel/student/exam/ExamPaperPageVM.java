package com.web.back.viewmodel.student.exam;

import com.web.back.base.BasePage;
import io.swagger.models.auth.In;

import javax.validation.constraints.NotNull;

/**
 * @author by hongdou
 * @date 2023/7/2.
 * @DESC:
 */
public class ExamPaperPageVM extends BasePage {
    @NotNull
    private Integer paperType;

    private Integer subjectId;

//    private Integer levelId;
//    private Integer infoClassContentID;
    private String courseName;
    //新增stuaccount
    private String stuAccount;

    private Integer is_blindbox;
    private Integer is_pk;

    public Integer getPaperType(){
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }


    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getStuAccount(){
        return stuAccount;
    }

    public void setStuAccount(String stuAccount){
        this.stuAccount = stuAccount;
    }

    public Integer getIs_blindbox(){
        return is_blindbox;
    }

    public void setIs_blindbox(Integer is_blindbox){
        this.is_blindbox = is_blindbox;
    }

    public Integer getIs_pk(){
        return  is_pk;
    }

    public void setIs_pk(Integer is_pk){
        this.is_pk = is_pk;
    }

}
