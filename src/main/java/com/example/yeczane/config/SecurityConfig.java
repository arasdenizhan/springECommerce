package com.example.yeczane.config;

import com.example.yeczane.model.enums.UserRoles;
import com.example.yeczane.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String LOGIN_URL = "/login";

    @Configuration
    @Order(1)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {

        private final MembersService membersService;
        private final ApplicationContext applicationContext;

        @Autowired
        public UserSecurityConfig(MembersService membersService, ApplicationContext applicationContext) {
            this.membersService = membersService;
            this.applicationContext = applicationContext;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(membersService)
                    .passwordEncoder(applicationContext.getBean(BCryptPasswordEncoder.class));
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user*").authorizeRequests()
                    .anyRequest()
                    .hasRole(String.valueOf(UserRoles.USER)).and()
                    .formLogin().loginPage(LOGIN_URL)
                    .loginProcessingUrl("/userAuth")
                    .permitAll().and()
                    .logout().permitAll()
                    .and().csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        private final MembersService membersService;
        private final ApplicationContext applicationContext;

        @Autowired
        public AdminSecurityConfig(MembersService membersService, ApplicationContext applicationContext) {
            this.membersService = membersService;
            this.applicationContext = applicationContext;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(membersService)
                    .passwordEncoder(applicationContext.getBean(BCryptPasswordEncoder.class));
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin*").authorizeRequests()
                    .anyRequest()
                    .hasRole(String.valueOf(UserRoles.ADMIN)).and()
                    .formLogin().loginPage(LOGIN_URL+"Admin")
                    .loginProcessingUrl("/adminAuth")
                    .permitAll().and()
                    .logout().permitAll()
                    .and().csrf().disable();
        }
    }

    @Configuration
    @Order(3)
    public static class GeneralSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/**/*.css", "/register", "/api/*")
                    .permitAll();
        }
    }
}



