package com.tss.ProjektJakubStasiurka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .authorizeRequests(auth -> auth
                .antMatchers("/", "/login", "/register", "/logout", "/whoami", "/ws/**", "/topic/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/borrows/**", "/books/**", "/actuatorInfo/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/books/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/books").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/borrows/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/borrows/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/homepage", true)
                .failureUrl("/login?error=true")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                )
                .csrf(csrf -> csrf
                .ignoringAntMatchers("/h2-console/**", "/api/**")
                )
                .headers(headers -> headers
                .frameOptions().disable()
                ).httpBasic();

        return http.build();
    }
}
