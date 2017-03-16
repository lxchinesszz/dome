package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Package: com.example.config
 * @Description: 权限配置
 * @author: liuxin
 * @date: 17/3/8 上午10:19
 */
public class SecurityConfig extends Object{
    @Autowired
    MyUserDetailsService detailsService;
//    WebSecurityConfigurerAdapter
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true)
//                .and().logout().logoutUrl("/logout")
//                .and().sessionManagement().maximumSessions(1).expiredUrl("/expired")
//                .and()
//                .and().exceptionHandling().accessDeniedPage("/accessDenied");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//    }
}
