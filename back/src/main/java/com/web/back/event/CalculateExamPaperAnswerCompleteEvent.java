package com.web.back.event;

import com.web.back.domain.ExamPaperAnswerInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @author by hongdou
 * @date 2023/7/13.
 * @DESC:
 */
public class CalculateExamPaperAnswerCompleteEvent extends ApplicationEvent {

    private final ExamPaperAnswerInfo examPaperAnswerInfo;

    /**
     * 实例化一个新的计算试卷答案完成事件
     */
    public CalculateExamPaperAnswerCompleteEvent(final ExamPaperAnswerInfo examPaperAnswerInfo){
        super(examPaperAnswerInfo);
        this.examPaperAnswerInfo = examPaperAnswerInfo;

    }

    /**
     *获取试卷答案信息
     */
    public ExamPaperAnswerInfo getExamPaperAnswerInfo(){
        return examPaperAnswerInfo;
    }
}
