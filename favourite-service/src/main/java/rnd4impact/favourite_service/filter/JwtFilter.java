package rnd4impact.favourite_service.filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import rnd4impact.favourite_service.jwt.JwtHelper;

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
            // Extract the Authorization header
            String bearerToken = request.getHeader("Authorization");

            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.substring(7);

                if (!token.isEmpty()) {
                    String data = jwtHelper.decodeToken(token); // Decode the JWT token

                    if (data != null) {
                        // Parse the roles from the token
                        Type listType = new TypeToken<List<SimpleGrantedAuthority>>() {}.getType();
                        List<GrantedAuthority> listRoles = gson.fromJson(data, listType);

                        // Set the authentication in the security context
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
        } catch (Exception ex) {
            // Handle the exception and set an appropriate HTTP status
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + ex.getMessage());
            return; // Stop the filter chain
        }
        // Continue the filter chain for valid requests
        filterChain.doFilter(request, response);
    }
}
