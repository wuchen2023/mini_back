package com.web.back.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.back.domain.QuestionAnswer;
import com.web.back.domain.TextContent;
import com.web.back.domain.enums.QuestionTypeEnum;
import com.web.back.domain.other.KeyValue;
import com.web.back.domain.other.QuestionAnswerUpdate;
import com.web.back.mapper.QuestionAnswerMapper;
import com.web.back.service.QuestionAnswerService;
import com.web.back.service.TextContentService;
import com.web.back.utils.DateTimeUtil;
import com.web.back.utils.ExamUtil;
import com.web.back.utils.JsonUtil;
import com.web.back.viewmodel.student.question.QuestionAnswerPageRequestVM;
import com.web.back.viewmodel.student.question.QuestionSubmitItemVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
@Service
public class QuestionAnswerServiceImpl extends BaseServiceImpl<QuestionAnswer> implements QuestionAnswerService {

    private final QuestionAnswerMapper questionAnswerMapper;

    private final TextContentService textContentService;

    @Autowired
    public QuestionAnswerServiceImpl(QuestionAnswerMapper questionAnswerMapper, TextContentService textContentService) {
        super(questionAnswerMapper);
        this.questionAnswerMapper = questionAnswerMapper;
        this.textContentService = textContentService;
    }

    @Override
    public PageInfo<QuestionAnswer> answerHistoryPage(QuestionAnswerPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                questionAnswerMapper.answerHistoryPage(requestVM));
    }

    @Override
    public List<QuestionAnswer> selectListByQuestionId(Integer id) {
        return questionAnswerMapper.selectListByQuestionId(id);
    }

    @Override
    public void insertList(List<QuestionAnswer> questionAnswers) {
        questionAnswerMapper.insertList(questionAnswers);
    }

    @Override
    public QuestionSubmitItemVM quesionAnswerToVM(QuestionAnswer qa) {
        QuestionSubmitItemVM questionSubmitItemVM = new QuestionSubmitItemVM();

        questionSubmitItemVM.setId(qa.getId());
        questionSubmitItemVM.setQuestionId(qa.getQuestionId());
        questionSubmitItemVM.setDoRight(qa.getDoRight());
        questionSubmitItemVM.setItemOrder(qa.getItemOrder());
        questionSubmitItemVM.setQuestionScore(ExamUtil.scoreToVM(qa.getQuestionScore()));
        questionSubmitItemVM.setScore(ExamUtil.scoreToVM(qa.getCustomerScore()));
        setSpecialToVM(questionSubmitItemVM, qa);
        return questionSubmitItemVM;
    }

    @Override
    public Integer selectAllCount(){
        return questionAnswerMapper.selectAllCount();
    }

    @Override
    public List<Integer> selectMothCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<KeyValue> mouthCount = questionAnswerMapper.selectCountByDate(startTime, endTime);
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    public int updateScore(List<QuestionAnswerUpdate> questionAnswerUpdates) {
        return questionAnswerMapper.updateScore(questionAnswerUpdates);
    }

    private void setSpecialToVM(QuestionSubmitItemVM questionSubmitItemVM, QuestionAnswer questionAnswer) {
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(questionAnswer.getQuestionType());
        switch (questionTypeEnum) {
            case MultipleChoice:
                questionSubmitItemVM.setContent(questionAnswer.getAnswer());
                questionSubmitItemVM.setContentArray(ExamUtil.contentToArray(questionAnswer.getAnswer()));
                break;
            case GapFilling:
                TextContent textContent = textContentService.selectById(questionAnswer.getTextContentId());
                List<String> correctAnswer = JsonUtil.toJsonListObject(textContent.getContent(), String.class);
                questionSubmitItemVM.setContentArray(correctAnswer);
                break;
            default:
                if (QuestionTypeEnum.needSaveTextContent(questionAnswer.getQuestionType())) {
                    TextContent content = textContentService.selectById(questionAnswer.getTextContentId());
                    questionSubmitItemVM.setContent(content.getContent());
                } else {
                    questionSubmitItemVM.setContent(questionAnswer.getAnswer());
                }
                break;
        }
    }
}
