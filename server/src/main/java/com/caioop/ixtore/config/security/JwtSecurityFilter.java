package com.caioop.ixtore.config.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.caioop.ixtore.services.AuthorizationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;
import java.util.Objects;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthorizationService authorizationService;

    private final HandlerExceptionResolver resolver;

    public JwtSecurityFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            //removes the "Bearer "
            final String token = this.recoverToken(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userEmail = jwtService.validateToken(token);

            if (!Objects.equals(userEmail, "") && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = this.authorizationService.loadUserByUsername(userEmail);
                if (user.getUsername().equals(userEmail)) {
                    System.out.println(user.getUsername());
                    System.out.println(userEmail);
                    var authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
//                authToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            resolver.resolveException(request,response,null,e);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "").trim();
    }
}
