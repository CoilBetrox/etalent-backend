package com.etalent.etalent_backend.config;

import com.etalent.etalent_backend.security.JwtAuthenticationEntryPoint;
import com.etalent.etalent_backend.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@ComponentScan(basePackages = "com.etalent.etalent_backend.security")
public class SecurityConfig {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers(SecurityConstants.AUTH_REGISTER_URL, SecurityConstants.AUTH_LOGIN_URL, SecurityConstants.AUTH_VERIFY_EMAIL_URL, SecurityConstants.AUTH_RESET_PASSWORD, SecurityConstants.AUTH_FORGOT_PASSWORD).permitAll()
                            .requestMatchers(SecurityConstants.ADMIN_PROFILE_URL).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO, SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN)
                            .requestMatchers(SecurityConstants.ADMIN_PROFILE_UPDATE_URL).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO, SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN)
                            .requestMatchers(SecurityConstants.ADMIN_BY_ROLE_URL).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.USERS_BY_ADMIN_URL).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.USERS_GET).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA)
                            .requestMatchers(SecurityConstants.CURSOS_BY_ADMINID).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.USERS_BY_CURSO).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.ASSIGN_USERS_TO_CURSO).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.DEL_USERS_TO_CURSO).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN_DO)

                            .requestMatchers(SecurityConstants.FIND_USERS_BY_SAP).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_TIENDA, SecurityConstants.ROLE_ADMIN_DO)
                            .requestMatchers(SecurityConstants.CHANGE_ADMINS_BY_ID).hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO)

                            .requestMatchers("/api/admins/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO, SecurityConstants.ROLE_ADMIN_TIENDA)
                            .requestMatchers("/api/usuarios/**").hasAnyAuthority(SecurityConstants.ROLE_ADMIN_DO, SecurityConstants.ROLE_ADMIN_TIENDA);
                    authorize.anyRequest().authenticated();
                });
        http.exceptionHandling( exeption -> exeption
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins("http://localhost:8080") // Especifique el origen exacto
                        .allowedOrigins("https://etalent.rdtroyaram.site") // Especifique el origen exacto
                        .allowedMethods("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}