package com.rnd4impact.user_service.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rnd4impact.user_service.jwt.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> bearerToken = Optional.ofNullable(request.getHeader("Authorization"));
        if (bearerToken.isPresent()) {
            String token = bearerToken.get().substring(7);
            if (!token.isEmpty()) {
                String data = jwtHelper.decodeToken(token);
                //custom type to Gson parse List
                Type listType = new TypeToken<List<SimpleGrantedAuthority>>() {}.getType();
                List<GrantedAuthority> listRoles = gson.fromJson(data, listType);
                System.out.println("Test role:" + data);
                if (data != null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "", listRoles);
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
            }

        }

        filterChain.doFilter(request,response);
    }
}
