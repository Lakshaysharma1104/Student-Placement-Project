package com.companyservice.companyservice.filter;

import com.companyservice.companyservice.service.JWTService;
import com.companyservice.companyservice.service.UserDetailServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTService  jwtService;
    private UserDetailServiceImp userDetailsService;
    @Override
    @NullMarked
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authentication = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        if(authentication != null && authentication.startsWith("Bearer ")){
            token =   authentication.substring(7);
            userName = jwtService.extractUsername(token);
            if(userName!=null){
                UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
                if(jwtService.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userName, null,userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }
        }
         filterChain.doFilter(request,response);
    }
}
