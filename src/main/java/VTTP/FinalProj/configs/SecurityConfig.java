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
            .authorizeHttpRequests().requestMatchers("/auth/**", "/", "/3rdpartylicenses.txt", "/", "/favicon.ico", "/index.html", "/main.ea62c963b825f252.js", "/polyfills.d81bfc265aeaed00.js", "/runtime.924b27b870857f62.js", "/styles.c0cf53b324e9d2ff.css", "/assets/**", "/Dreamcatcher.11b799e9474b39a0.png")
            .permitAll().anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authenticationProvider(authProvider)
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
    }

}

