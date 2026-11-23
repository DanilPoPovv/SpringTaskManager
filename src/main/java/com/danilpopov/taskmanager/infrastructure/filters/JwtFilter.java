package com.danilpopov.taskmanager.infrastructure.filters;

import com.danilpopov.taskmanager.application.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")){
            try{
                String jwt = request.getHeader("Authorization").substring(7);
                if(jwt.isEmpty()) {
                    response.sendError(400);
                    return;
                }
                Claims claims = jwtService.verifyJwt(jwt);
                if(!claims.get("type",String.class).equals("ACCESS")){
                    response.sendError(400);
                    return;
                }
                List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(claims.get("role", String.class)));
                Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,grantedAuthorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request,response);
            }
            catch(JwtException jwtException){
                response.sendError(401);
            }
        }
        else {
            filterChain.doFilter(request,response);
        }

    }
}
