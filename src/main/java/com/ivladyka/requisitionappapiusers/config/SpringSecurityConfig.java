package com.ivladyka.requisitionappapiusers.config;


import com.ivladyka.requisitionappapiusers.service.UserService;
import com.ivladyka.requisitionappapiusers.util.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationProvider auththenticationProvider;
    private UserService userService;

    @Autowired
    public SpringSecurityConfig(UserService userService, CustomAuthenticationProvider authenticationProvider) {
        this.auththenticationProvider = authenticationProvider;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.cors().and().csrf().disable()
                        .authorizeRequests().antMatchers("reqI").hasAuthority("ROLE_USER").and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().loginPage("http://localhost:4200/home").permitAll()
                .defaultSuccessUrl("http://localhost:8082/users/status/check");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder http) throws Exception {
        http.authenticationProvider(auththenticationProvider);
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
