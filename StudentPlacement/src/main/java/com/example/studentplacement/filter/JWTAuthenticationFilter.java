package com.example.studentplacement.filter;

import com.example.studentplacement.service.JWTService;
import com.example.studentplacement.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@AllArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailServiceImpl userDetail;
    @Override
    @NullMarked
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         String authorizationHeader = request.getHeader("Authorization");
         String username = null;
         String token = null;
         if(authorizationHeader !=null &&  authorizationHeader.startsWith("Bearer ")){
             token = authorizationHeader.substring(7);
              username = jwtService.extractUsername(token);
         }
         if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails = userDetail.loadUserByUsername(username);
             if(jwtService.isTokenValid(token,userDetails)){
                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetail
                         ,null,
                         userDetails.getAuthorities());
                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(auth);

             }

         }
        filterChain.doFilter(request, response);


    }
}
