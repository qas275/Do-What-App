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
        
        // httpSecurity.cors().and().csrf().disable()
        //     .authorizeHttpRequests().requestMatchers("/auth/**", "/", "https://curly-friction-production.up.railway.app/", "https://curly-friction-production.up.railway.app/#/", "https://curly-friction-production.up.railway.app/#/auth/**", "https://curly-friction-production.up.railway.app/#/auth/login", "https://curly-friction-production.up.railway.app/#/auth/register")
        //     .permitAll().anyRequest().authenticated()
        //     .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //     .and().authenticationProvider(authProvider)
        //     .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.cors().and().csrf().disable()
            .authorizeHttpRequests().requestMatchers("/auth/**", "/", "/3rdpartylicenses.txt", "/", "/favicon.ico", "/index.html", "/main.827ba58962a0432d.js", "/manifest.webmanifest", "/polyfills.d81bfc265aeaed00.js", "/runtime.924b27b870857f62.js", "/styles.999bc18a00f2fb4c.css", "/assets/**", "/images/**","/icon-192x192.png","/icon-256x256.png","/icon-384x384.png","/icon-512x512.png", "/Dreamcatcher.11b799e9474b39a0.png")
            .permitAll().anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authenticationProvider(authProvider)
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
    }

}

