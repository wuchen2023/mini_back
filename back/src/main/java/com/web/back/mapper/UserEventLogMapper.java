package com.web.back.mapper;

import com.web.back.domain.UserEventLog;
import com.web.back.domain.other.KeyValue;
import com.web.back.viewmodel.admin.user.UserEventPageRequestVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
@Mapper
public interface UserEventLogMapper extends BaseMapper<UserEventLog> {
    List<UserEventLog> getUserEventLogByUserId(Integer id);

    List<UserEventLog> page(UserEventPageRequestVM requestVM);

    List<KeyValue> selectCountByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
