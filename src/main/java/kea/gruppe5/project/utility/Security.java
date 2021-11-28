package kea.gruppe5.project.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    /*
     * Authentication processen bruger dette som udgangspunkt:
     * https://www.baeldung.com/spring-security-login
     */

    // Siden Spring 5 er det et krav at definere en passwordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        /*
         * For at konfigurere rute tilladelser osv. bliver HttpSecurity sat
         * Default login side bliver overskrevet og sat til en anden rute
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
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/auth/login?status=fail")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
