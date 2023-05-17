package com.web.back.context;

import com.web.back.domain.Teacher;
import com.web.back.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
@Component
public class WebContext {
    private static final String USER_ATTRIBUTES = "USER_ATTRIBUTES";

    private final TeacherService teacherService;

    @Autowired
    public WebContext(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    public void setCurrentUser(Teacher teacher){
        RequestContextHolder.currentRequestAttributes().setAttribute(USER_ATTRIBUTES, teacher, RequestAttributes.SCOPE_REQUEST);
    }

    public Teacher getCurrentUser(){
        Teacher teacher = (Teacher) RequestContextHolder.currentRequestAttributes().getAttribute(USER_ATTRIBUTES,RequestAttributes.SCOPE_REQUEST);
        if(null!=teacher){
            return teacher;
        }
        else{
            org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (null==springUser){
                return null;
            }
            teacher = teacherService.get_detail_by_account(springUser.getUsername());
            if (null!=teacher){
                setCurrentUser(teacher);
            }
            return teacher;
        }
    }
}
