package com.web.back.domain.question;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
public class QuestionObject {
    private String titleContent;

    private String analyze;

    private List<QuestionItemObject> questionItemObjects;

    private String correct;

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getAnalyze() {
        return analyze;
    }

    public void setAnalyze(String analyze) {
        this.analyze = analyze;
    }

    public List<QuestionItemObject> getQuestionItemObjects() {
        return questionItemObjects;
    }

    public void setQuestionItemObjects(List<QuestionItemObject> questionItemObjects) {
        this.questionItemObjects = questionItemObjects;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
