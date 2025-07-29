package com.ktk.ktkapp.utilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class secuConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) 

                
                .authorizeHttpRequests(authorize -> authorize
                        
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        
                
                        .requestMatchers(
                            "/api/auth/signup",
                            "/api/auth/login",
                            "/api/kiosk-clients/create",
                            "/api/kiosk-clients/{clientId}/link-rep/{clientRepId}",
                            "/api/kiosk-locations/add",
                            "/api/altron-hubs/create",
                            "/api/kiosk-types/**",
                            "/api/kiosk-statuses/**",
                            "/api/kiosks/**",
                            "/api/suppliers/**",
                            "/api/components/**",
                            "/api/kiosk-locations/**" 
                        ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // Provide the PasswordEncoder that matches passwordUtil's Argon2 configuration
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(
                16,         // saltLength (bytes) - common default
                32,         // hashLength (bytes) - common default
                1,          // parallelism (lanes) - matches passwordUtil
                64 * 1024,  // memory (KB) - matches passwordUtil (65536 bytes = 64KB)
                2           // iterations (cost) - matches  passwordUtil
        );
    }
}
