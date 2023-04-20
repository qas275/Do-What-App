package VTTP.FinalProj.controllers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import VTTP.FinalProj.services.JWTUtil;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorisation");
        System.out.println("FILTERING, RETRIEVED TOKEN: "+authHeader);
        System.out.println(authHeader);
        final String jwt;
        final String userEmail;
        if(authHeader == null || authHeader.startsWith("Bearer ")){ //added space after bearer unlike online sources to filter " " JWTs
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(6);
        System.out.println("JWT EXTRACTED: "+jwt);
        userEmail = jwtUtil.extractUsername(jwt);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userdetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtUtil.isTokenValid(jwt, userdetails)){
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request, response);
    }

    

    
}


// @Component
// public class JWTRequestFilter extends OncePerRequestFilter{
//     @Autowired
//     JWTUserDetailsService jwtUserDetailsService;

//     @Autowired
//     JwtTokenUtil jwtTokenUtil;
    
//     @Override
// 	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
// 			throws ServletException, IOException {

// 		final String requestTokenHeader = request.getHeader("Authorization");

// 		String username = null;
// 		String jwtToken = null;
// 		// JWT Token is in the form "Bearer token". Remove Bearer word and get
// 		// only the Token
// 		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
// 			jwtToken = requestTokenHeader.substring(7);
// 			try {
// 				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
// 			} catch (IllegalArgumentException e) {
// 				System.out.println("Unable to get JWT Token");
// 			} catch (ExpiredJwtException e) {
// 				System.out.println("JWT Token has expired");
// 			}
// 		} else {
// 			logger.warn("JWT Token does not begin with Bearer String");
// 		}

// 		// Once we get the token validate it.
// 		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

// 			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

// 			// if token is valid configure Spring Security to manually set
// 			// authentication
// 			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

// 				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
// 						userDetails, null, userDetails.getAuthorities());
// 				usernamePasswordAuthenticationToken
// 						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// 				// After setting the Authentication in the context, we specify
// 				// that the current user is authenticated. So it passes the
// 				// Spring Security Configurations successfully.
// 				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
// 			}
// 		}
// 		chain.doFilter(request, response);
// 	}
// }
