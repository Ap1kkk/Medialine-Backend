package ru.medialine.security;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.medialine.exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ru.medialine.model.User;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//public class JwtAuthenticationFilter extends GenericFilterBean {

    public static final String BEARER_PREFIX = "Bearer ";
    @Value("${jwt.header}")
    private String headerName;

    private final JwtAuthenticationProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtAuthenticationProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader(headerName);

        if(!StringUtils.hasLength(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token;

        if(StringUtils.startsWithIgnoreCase(authHeader, BEARER_PREFIX)) {
            token = authHeader.substring(BEARER_PREFIX.length());
        }
        else {
            token = authHeader;
        }
        try {
            String username = jwtTokenProvider.getUsername(token);

            if(StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {

                    if (jwtTokenProvider.validateToken(token)) {
                        Authentication authentication = jwtTokenProvider.getAuthentication(token);
                        if (authentication != null) {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }

            }
        }catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).sendError(e.getHttpStatus().value());
            throw new JwtAuthenticationException("JWT token is expired or invalid", e.getHttpStatus(), e);
        }
        filterChain.doFilter(request, response);
    }
}
