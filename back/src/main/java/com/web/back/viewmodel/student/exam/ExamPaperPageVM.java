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
    private Integer infoClassContentID;

    //新增stuaccount
    private String stuAccount;

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

    public Integer getInfoClassContentID(){
        return infoClassContentID;
    }

    public void setInfoClassContentID(Integer infoClassContentID){
        this.infoClassContentID = infoClassContentID;
    }

    public String getStuAccount(){
        return stuAccount;
    }

    public void setStuAccount(String stuAccount){
        this.stuAccount = stuAccount;
    }

//    public Integer getLevelId() {
//        return levelId;
//    }

//    public void setLevelId(Integer levelId) {
//        this.levelId = levelId;
//    }
}
