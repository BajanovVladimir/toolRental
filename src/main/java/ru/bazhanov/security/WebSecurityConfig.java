package ru.bazhanov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import ru.bazhanov.model.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private  UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/registration","/login").permitAll()
                        .requestMatchers("/","/orderModifyCardView").hasAnyRole(Role.ROLE_RENTER,Role.ROLE_LESSOR)
                        .requestMatchers("/lessor/**").hasRole(Role.ROLE_LESSOR)
                        .requestMatchers("/renter/**").hasRole(Role.ROLE_RENTER)
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .permitAll()
                        .successForwardUrl("/")

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }
}
