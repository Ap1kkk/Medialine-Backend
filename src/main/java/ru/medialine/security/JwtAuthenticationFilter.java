package ru.medialine.security;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.medialine.exception.resolver.RestResponseStatusExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//public class JwtAuthenticationFilter extends GenericFilterBean {

    public static final String BEARER_PREFIX = "Bearer ";
    @Value("${jwt.header}")
    private String headerName;

    private final JwtAuthenticationProvider jwtTokenProvider;
    private final HandlerExceptionResolver exceptionResolver;
    public JwtAuthenticationFilter(JwtAuthenticationProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.exceptionResolver = new RestResponseStatusExceptionResolver();
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
        }catch (Exception e) {
            SecurityContextHolder.clearContext();
            exceptionResolver.resolveException(request, response, null, e);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
