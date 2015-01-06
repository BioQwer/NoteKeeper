package com.bioqwer.serverApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("restAuthenticationExceptionHandler")
    @Autowired
    private SimpleUrlAuthenticationFailureHandler restError;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/fonts/**", "/js/**", "/index*", "/login*", "/app/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/user/singIn").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("j_username") /* BY DEFAULT IS username!!! */
                .passwordParameter("j_password") /* BY DEFAULT IS password!!! */
                .loginProcessingUrl("/j_spring_security_check")
                .failureHandler(restError)
                .defaultSuccessUrl("/user")
                .loginPage("/notLogin")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/notLogin")
                .invalidateHttpSession(true);
    }

    @Configuration
    protected static class AuthenticationConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Qualifier("userDetailsServiseImpl")
        @Autowired
        private UserDetailsService userDetailsServise;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.
                    userDetailsService(userDetailsServise);
        }

    }
}