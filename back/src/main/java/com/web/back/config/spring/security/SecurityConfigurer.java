package com.web.back.config.spring.security;

import com.web.back.config.property.CookieConfig;
import com.web.back.config.property.SystemConfig;
import com.web.back.domain.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author by hongdou
 * @date 2023/5/17.
 * @DESC:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private final SystemConfig systemConfig;
        private final LoginAuthenticationEntryPoint restAuthenticationEntryPoint;
        private final RestAuthenticationProvider restAuthenticationProvider;
        private final RestDetailsServiceImpl formDetailsService;
        private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
        private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;
        private final RestLogoutSuccessHandler restLogoutSuccessHandler;
        private final RestAccessDeniedHandler restAccessDeniedHandler;

        /**
         * Instantiates a new Form login web security configurer adapter.
         *
         * @param systemConfig                     the system config
         * @param restAuthenticationEntryPoint     the rest authentication entry point
         * @param restAuthenticationProvider       the rest authentication provider
         * @param formDetailsService               the form details service
         * @param restAuthenticationSuccessHandler the rest authentication success handler
         * @param restAuthenticationFailureHandler the rest authentication failure handler
         * @param restLogoutSuccessHandler         the rest logout success handler
         * @param restAccessDeniedHandler          the rest access denied handler
         */
        @Autowired
        public FormLoginWebSecurityConfigurerAdapter(SystemConfig systemConfig, LoginAuthenticationEntryPoint restAuthenticationEntryPoint, RestAuthenticationProvider restAuthenticationProvider, RestDetailsServiceImpl formDetailsService, RestAuthenticationSuccessHandler restAuthenticationSuccessHandler, RestAuthenticationFailureHandler restAuthenticationFailureHandler, RestLogoutSuccessHandler restLogoutSuccessHandler, RestAccessDeniedHandler restAccessDeniedHandler) {
            this.systemConfig = systemConfig;
            this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
            this.restAuthenticationProvider = restAuthenticationProvider;
            this.formDetailsService = formDetailsService;
            this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
            this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
            this.restLogoutSuccessHandler = restLogoutSuccessHandler;
            this.restAccessDeniedHandler = restAccessDeniedHandler;
        }

        //下面是自己添加的========================
//        private static final String[] AUTH_WHITELIST = {
//                // ... 包含需要忽略安全保护的URL
//                "/swagger-ui/**",
//                "/swagger-resources/**",
//                "/v2/api-docs",
//                "/webjars/**"
//        };
//
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring().antMatchers(AUTH_WHITELIST);
//        }

        //=====================================

        /**
         * @param http http
         * @throws Exception exception
         *                   csrf is the from submit get method
         */

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.headers().frameOptions().disable();

            List<String> securityIgnoreUrls = systemConfig.getSecurityIgnoreUrls();
//            String[] ignores = new String[securityIgnoreUrls.size()];
            if(securityIgnoreUrls == null){
                securityIgnoreUrls = new ArrayList<>();
            }
            String[] ignores = new String[securityIgnoreUrls.size()];
            http
                    .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                    .and().authenticationProvider(restAuthenticationProvider)
                    .authorizeRequests()
                    .antMatchers(securityIgnoreUrls.toArray(ignores)).permitAll()
                    .antMatchers("/api/webadmin/**").hasRole(RoleEnum.ADMIN.getName())
                    .anyRequest().permitAll()
                    .and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler)
                    .and().formLogin().successHandler(restAuthenticationSuccessHandler).failureHandler(restAuthenticationFailureHandler)
                    .and().logout().logoutUrl("/api/user/logout").logoutSuccessHandler(restLogoutSuccessHandler).invalidateHttpSession(true)
                    .and().rememberMe().key(CookieConfig.getName()).tokenValiditySeconds(CookieConfig.getInterval()).userDetailsService(formDetailsService)
                    .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and().csrf().disable()
                    .cors().configurationSource(request -> {
                CorsConfiguration cors = new CorsConfiguration();
                cors.setAllowedOrigins(Arrays.asList("http://1.117.75.111:8003"));
                cors.setAllowedMethods(Arrays.asList("GET","POST"));
                cors.setAllowCredentials(true);
                return cors;
            });;
        }


        /**
         * Cors configuration source cors configuration source.
         *
         * @return the cors configuration source
         */
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setMaxAge(3600L);
            configuration.setAllowedOrigins(Collections.singletonList("*"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/api/**", configuration);
            return source;
        }


        /**
         * Authentication filter rest login authentication filter.
         *
         * @return the rest login authentication filter
         * @throws Exception the exception
         */
        @Bean
        public RestLoginAuthenticationFilter authenticationFilter() throws Exception {
            RestLoginAuthenticationFilter authenticationFilter = new RestLoginAuthenticationFilter();
            authenticationFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
            authenticationFilter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
            authenticationFilter.setAuthenticationManager(authenticationManagerBean());
            authenticationFilter.setUserDetailsService(formDetailsService);
            return authenticationFilter;
        }


    }
}
