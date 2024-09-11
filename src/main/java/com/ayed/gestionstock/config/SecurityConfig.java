package com.ayed.gestionstock.config;

import com.ayed.gestionstock.service.auth.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private ApplicationUserDetailsService applicationUserDetailsService;
  private ApplicationRequestFilter applicationRequestFilter;

    public SecurityConfig(ApplicationUserDetailsService applicationUserDetailsService, ApplicationRequestFilter applicationRequestFilter) {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.applicationRequestFilter = applicationRequestFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(applicationUserDetailsService)
    .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(corsFilter(),SessionManagementFilter.class)
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/**/authenticate",
                    "/**/entreprises/create",
                    "/v2/api-docs",
                    "/swagger-resources",
                    "/swagger-resources/**",
                    "/configuration/ui",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // Don't do this in production, use a proper list  of allowed origins
    config.setAllowedOriginPatterns(Collections.singletonList("*"));
    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    source.registerCorsConfiguration("/**", config);
    // some comment here
    return new CorsFilter(source);
  }


  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}