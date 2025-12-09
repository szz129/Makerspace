package com.makerspace.makerspaceapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.makerspace.makerspaceapp.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enable @PreAuthorize annotations
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//constructor injection
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/users/register").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                
                // User endpoints - All authenticated users
                .requestMatchers("/api/users").hasAnyRole("USER", "ADMIN", "STAFF")
                .requestMatchers("/api/users/{id}").hasAnyRole("USER", "ADMIN", "STAFF")
                
                // Admin only endpoints
                .requestMatchers("/api/users/{id}/role").hasRole("ADMIN")
                
                // Makerspace - Read for all, write for ADMIN/STAFF
                .requestMatchers("GET", "/api/makerspaces/**").hasAnyRole("USER", "ADMIN", "STAFF")
                .requestMatchers("POST", "/api/makerspaces/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("PUT", "/api/makerspaces/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("DELETE", "/api/makerspaces/**").hasRole("ADMIN")
                
                // Tools - Read for all, write for ADMIN/STAFF
                .requestMatchers("GET", "/api/tools/**").hasAnyRole("USER", "ADMIN", "STAFF")
                .requestMatchers("POST", "/api/tools/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("PUT", "/api/tools/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("PATCH", "/api/tools/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("DELETE", "/api/tools/**").hasRole("ADMIN")
                
                // Projects - Users can manage their own
                .requestMatchers("/api/projects/**").hasAnyRole("USER", "ADMIN", "STAFF")
                
                // Events - Read for all, write for ADMIN/STAFF
                .requestMatchers("GET", "/api/events/**").hasAnyRole("USER", "ADMIN", "STAFF")
                .requestMatchers("POST", "/api/events/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("PUT", "/api/events/**").hasAnyRole("ADMIN", "STAFF")
                .requestMatchers("DELETE", "/api/events/**").hasAnyRole("ADMIN", "STAFF")
                
                // YouTube - All authenticated users
                .requestMatchers("/api/youtube/**").hasAnyRole("USER", "ADMIN", "STAFF")
                
                // Everything else requires authentication
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
