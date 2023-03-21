package com.contactmangementservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RequestFilter requestFilter;

    @Autowired
    public SecurityConfig(RequestFilter requestFilter) {
        this.requestFilter = requestFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.headers().xssProtection().disable();
        http.csrf().disable().logout().disable().formLogin().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().anonymous().and().exceptionHandling()
                .authenticationEntryPoint((req, rsp, e) -> {
                    rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    e.printStackTrace();
                }).and().addFilterAfter(requestFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .antMatchers(HttpMethod.GET, "/contact-management-service/swagger-ui/").permitAll()
                .anyRequest().authenticated().and().exceptionHandling().accessDeniedHandler(restAccessDeniedHandler());
    }

    @Bean
    RestAccessDeniedHandler restAccessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }
}
