package com.web.back.config.spring.security;

import com.web.back.base.SystemCode;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:webadmin登录入口
 */
@Component
@CrossOrigin("http://1.117.75.111:8003")
public final class LoginAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public LoginAuthenticationEntryPoint(){
        super("/api/user/login");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        RestUtil.response(response, SystemCode.UNAUTHORIZED);
    }
}
