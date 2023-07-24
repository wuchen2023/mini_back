package com.web.back.viewmodel.admin.qiangda;

import io.swagger.models.auth.In;

/**
 * @author by hongdou
 * @date 2023/7/24.
 * @DESC:
 */
public class StuAddQiangDaVM {
    private  Integer id;

    private String qiangda_type;
    private String question;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQiangda_type() {
        return qiangda_type;
    }

    public void setQiangda_type(String qiangda_type) {
        this.qiangda_type = qiangda_type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
