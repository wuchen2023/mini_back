package com.web.back.mapper;

import com.web.back.domain.QuestionAnswer;
import com.web.back.domain.other.KeyValue;
import com.web.back.domain.other.QuestionAnswerUpdate;
import com.web.back.viewmodel.student.question.QuestionAnswerPageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/10.
 * @DESC:
 */
@Mapper
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
    List<QuestionAnswer> selectListByQuestionId(Integer id);

    List<QuestionAnswer> answerHistoryPage(QuestionAnswerPageRequestVM requestVM);

    int insertList(List<QuestionAnswer> list);

    Integer selectAllCount();

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int updateScore(List<QuestionAnswerUpdate> questionAnswers);
}
