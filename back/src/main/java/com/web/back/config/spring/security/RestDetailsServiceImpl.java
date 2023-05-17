package com.web.back.config.spring.security;

import com.web.back.domain.enums.RoleEnum;
import com.web.back.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:验证通过之后，第二、第三。。。请求会调用此类
 */
@Component
public class RestDetailsServiceImpl implements UserDetailsService {
    //下面就用到了老师的服务
    private final TeacherService teacherService;

    @Autowired
    public RestDetailsServiceImpl(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    //这里所谓的依据用户名获取对应用户，实际对应的是老师实体，用户名采用的账户account进行对照的
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException{
        com.web.back.domain.Teacher teacher = teacherService.get_detail_by_account(account);
        if (teacher==null){
            throw new UsernameNotFoundException("您输入的account不存在！");
        }

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(RoleEnum.fromCode(teacher.getRole()).getRoleName()));
        return new User(teacher.getAccount(), teacher.getPassword(),grantedAuthorities);
    }
}
