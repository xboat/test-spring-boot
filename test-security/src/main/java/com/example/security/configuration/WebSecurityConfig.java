package com.example.security.configuration;

import com.example.security.filter.BeforeLoginFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author xboat date 2019-01-16
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] pathArray ={"/home","/test","/login","/hello","/logout","/logout.html"};
        BeforeLoginFilter loginFilter = new BeforeLoginFilter();
        http
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/user/**").hasRole("USER")
                .antMatchers(pathArray).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage( "/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/logout.html")
                .permitAll()
                .and()
                .addFilterBefore(
                        loginFilter,
                        BasicAuthenticationFilter.class);

       //http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
        http.exceptionHandling().authenticationEntryPoint(loginFilter);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                .antMatchers("/", "/admin/**", "/account/**", "/index.html"
//                        , "/assets/**", "/static/**", "/public/**", "/**/favicon.ico");
    }
}
