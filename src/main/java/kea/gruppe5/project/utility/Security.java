package kea.gruppe5.project.utility;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        /*
        * For at konfigurere rute tilladelser osv. bliver HttpSecurity sat vha. Spring Security dokumentationen
        * https://spring.io/guides/gs/securing-web/
        */
        security
        .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/projects/**").hasRole("user")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/auth/login")
            .failureUrl("/auth/login?status=fail")
            .permitAll()
            .and()
        .logout()
            .permitAll();
    }
}
