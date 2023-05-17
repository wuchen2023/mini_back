package com.web.back.service;

import com.github.pagehelper.PageInfo;
import com.web.back.domain.UserEventLog;
import com.web.back.viewmodel.admin.user.UserEventPageRequestVM;

import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
public interface UserEventLogService extends BaseService<UserEventLog> {

    List<UserEventLog> getUserEventLogByUserId(Integer id);

    PageInfo<UserEventLog> page(UserEventPageRequestVM requestVM);

    List<Integer> selectMothCount();
}
