package com.student.cq.config;

import com.student.cq.filter.TokenAuthenticationFilter;
import com.student.cq.handler.UserAccessDeniedHandler;
import com.student.cq.handler.UserAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;

/**
 * 安全配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Resource
    UserAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    TokenAuthenticationFilter tokenAuthenticationFilter;
    @Resource
    UserAccessDeniedHandler userAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/login").permitAll() //设置开放API路径
                .anyRequest().authenticated()                 //对其它请求进行认证
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)     //当身份认证未通过时执行的操作
                .and()
                .exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)           //当身份认证通后但权限不足时执行的操作
                .and()
                .csrf().disable()   //禁用CSRF
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //关闭Session
                .and()
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
