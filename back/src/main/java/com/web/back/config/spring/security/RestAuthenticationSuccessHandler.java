package com.web.back.config.spring.security;

import com.web.back.base.SystemCode;
import com.web.back.domain.UserEventLog;
import com.web.back.event.UserEvent;
import com.web.back.mapper.UserEventLogMapper;
import com.web.back.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:登录成功后的处理，写入数据库的记录操作
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final ApplicationEventPublisher eventPublisher;
    private final TeacherService teacherService;

    private final UserEventLogMapper userEventLogMapper;

    @Autowired
    public RestAuthenticationSuccessHandler(ApplicationEventPublisher eventPublisher, TeacherService teacherService,UserEventLogMapper userEventLogMapper) {
        this.eventPublisher = eventPublisher;
        this.teacherService = teacherService;
        this.userEventLogMapper=userEventLogMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
        Object object = authentication.getPrincipal();
        if(null !=object){
            User springUser = (User) object;
            com.web.back.domain.Teacher teacher = teacherService.get_detail_by_account(springUser.getUsername());
            if(null != teacher){
                System.out.println("RestAuthenticationSuccessHandler.java:"+"登录后写入日志处理");
                System.out.println("id为："+teacher.getId()+"\n"+"账户："+teacher.getAccount());
                UserEventLog userEventLog = new UserEventLog(teacher.getId(),teacher.getAccount(),new Date());
                userEventLog.setContent("老师账户："+teacher.getAccount() + "登录了喜尔课堂管理后台");
                userEventLogMapper.insert(userEventLog);
                System.out.println("写入成功");
                eventPublisher.publishEvent(new UserEvent(userEventLog));
                com.web.back.domain.Teacher newTeacher = new com.web.back.domain.Teacher();
                newTeacher.setAccount(teacher.getAccount());
                RestUtil.response(response, SystemCode.OK.getCode(), SystemCode.OK.getMessage(), newTeacher);

            }
        }else{
            RestUtil.response(response, SystemCode.UNAUTHORIZED.getCode(), SystemCode.UNAUTHORIZED.getMessage());
        }
    }
}
