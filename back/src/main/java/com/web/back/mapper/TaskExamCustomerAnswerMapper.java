package com.web.back.mapper;

import com.web.back.domain.TaskExamCustomerAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/6/22.
 * @DESC:
 */
@Mapper
public interface TaskExamCustomerAnswerMapper extends BaseMapper<TaskExamCustomerAnswer> {

    TaskExamCustomerAnswer getByTUid(@Param("tid") Integer tid, @Param("uid") Integer uid);

    List<TaskExamCustomerAnswer> selectByTUid(@Param("taskIds") List<Integer> taskIds, @Param("uid") Integer uid);
}

