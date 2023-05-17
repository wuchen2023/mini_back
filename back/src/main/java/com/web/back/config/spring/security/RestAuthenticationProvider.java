package com.web.back.config.spring.security;

import com.web.back.context.WebContext;
import com.web.back.domain.enums.RoleEnum;
import com.web.back.service.AuthenticationService;
import com.web.back.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:用于登录用户进行密码验证
 */
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;

    private final TeacherService teacherService;

    private final WebContext webContext;

    @Autowired
    public RestAuthenticationProvider(AuthenticationService authenticationService, TeacherService teacherService, WebContext webContext) {
        this.authenticationService = authenticationService;
        this.teacherService = teacherService;
        this.webContext = webContext;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String username = authentication.getName();

        String password = (String) authentication.getCredentials();
        System.out.println("password为："+password);

        com.web.back.domain.Teacher teacher = teacherService.get_detail_by_account(username);

        if(teacher ==null){
            throw new UsernameNotFoundException("账户名或密码错误！");
        }

        boolean result = authenticationService.authUser(teacher, username,password);

        if (!result){
            throw new BadCredentialsException("账户名或密码错误！");

        }

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(teacher.getRole()).getRoleName()));

        User authUser = new User(teacher.getAccount(), teacher.getPassword(),grantedAuthorities);

        return new UsernamePasswordAuthenticationToken(authUser,authUser.getPassword(),authUser.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> aClass){
        return true;
    }

}
