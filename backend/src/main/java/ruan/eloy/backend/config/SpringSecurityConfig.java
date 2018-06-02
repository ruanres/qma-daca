package ruan.eloy.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ruan.eloy.backend.security.JwtAuthenticationEntryPoint;
import ruan.eloy.backend.security.StudentDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final StudentDetailsService studentDetailsService;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    public SpringSecurityConfig(StudentDetailsService studentDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.studentDetailsService = studentDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

//    JwtAuthenticationFilter

    /**
     * AuthenticationManagerBuilder is used to create
     * an AuthenticationManager instance which is the
     * main Spring Security interface for authenticating a user.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/student")
                        .permitAll()
                .anyRequest()
                    .authenticated();
    }
}
