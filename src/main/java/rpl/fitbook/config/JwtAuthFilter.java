package rpl.fitbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import rpl.fitbook.exception.AuthException;
import rpl.fitbook.service.auth.JwtService;
import rpl.fitbook.service.security.UserDetailsServiceImpl;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String JWT_HEADER = "Authorization";
    private static final String JWT_TOKEN_PREFIX = "Bearer";

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(JWT_HEADER);
        final String jwtToken;
        final String userEmail;
        try {

            if (authHeader == null || !authHeader.startsWith(JWT_TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.substring(7);
            userEmail = jwtService.extractEmail(jwtToken);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new AuthException("Invalid JWT Token");
        }
    }
}
