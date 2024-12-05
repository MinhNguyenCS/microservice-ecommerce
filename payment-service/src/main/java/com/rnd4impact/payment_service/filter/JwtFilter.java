package com.rnd4impact.payment_service.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rnd4impact.payment_service.jwt.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;
    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);
                if (!token.isEmpty()) {
                    String data = jwtHelper.decodeToken(token);
                    if (data != null) {
                        Type listType = new TypeToken<List<SimpleGrantedAuthority>>() {
                        }.getType();
                        List<GrantedAuthority> listRoles = gson.fromJson(data, listType);
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(null, null, listRoles);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {
                        throw new RuntimeException("Invalid token payload.");
                    }
                } else {
                    throw new RuntimeException("Empty token.");
                }
            } else {
                throw new RuntimeException("Missing or malformed Authorization header.");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
