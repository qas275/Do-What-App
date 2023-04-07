package VTTP.FinalProj.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import VTTP.FinalProj.controllers.JWTRequestFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JWTRequestFilter jwtRequestFilter;

    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        
        httpSecurity.csrf().disable()
            .authorizeHttpRequests().requestMatchers("/auth/**")
            .permitAll().anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authenticationProvider(authProvider)
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

            return httpSecurity.build();
    }

}

