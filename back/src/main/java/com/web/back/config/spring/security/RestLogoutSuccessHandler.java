package com.web.back.config.spring.security;

import com.web.back.base.SystemCode;
import com.web.back.domain.Teacher;
import com.web.back.domain.UserEventLog;
import com.web.back.event.UserEvent;
import com.web.back.mapper.UserEventLogMapper;
import com.web.back.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;



@Component
public class RestLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final ApplicationEventPublisher eventPublisher;
    private final TeacherService teacherService;

    private final UserEventLogMapper userEventLogMapper;

    /**
     * Instantiates a new Rest logout success handler.
     *
     * @param eventPublisher the event publisher
     * @param teacherService    the user service
     */
    @Autowired
    public RestLogoutSuccessHandler(ApplicationEventPublisher eventPublisher, TeacherService teacherService,UserEventLogMapper userEventLogMapper) {
        this.eventPublisher = eventPublisher;
        this.teacherService = teacherService;
        this.userEventLogMapper = userEventLogMapper;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        if (null != springUser) {
            System.out.println("RestAuthenticationSuccessHandler.java:"+"登出写入日志处理");
            System.out.println("账户："+springUser.getUsername());

            Teacher teacher = teacherService.get_detail_by_account(springUser.getUsername());
            UserEventLog userEventLog = new UserEventLog(teacher.getId(), teacher.getAccount(),  new Date());
            userEventLog.setContent("老师账户："+teacher.getAccount() + " 登出了喜尔课堂管理后台");
            //新增以下语句写入
            userEventLogMapper.insert(userEventLog);
            System.out.println("写入成功");

            eventPublisher.publishEvent(new UserEvent(userEventLog));
        }
        RestUtil.response(response, SystemCode.OK);
    }
}
