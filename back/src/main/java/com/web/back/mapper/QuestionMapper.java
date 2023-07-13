package com.web.back.mapper;


import com.web.back.domain.Question;
import com.web.back.domain.other.KeyValue;
import com.web.back.viewmodel.admin.question.QuestionPageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/8.
 * @DESC:
 */
//public interface QuestionMapper extends BaseMapper<Question> {
//}


@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    List<Question> page(QuestionPageRequestVM requestVM);

    List<Question> selectByIds(@Param("ids") List<Integer> ids);

    Integer selectAllCount();

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<Integer> findAllQuestionIds(); //查询所有的问题id
}

