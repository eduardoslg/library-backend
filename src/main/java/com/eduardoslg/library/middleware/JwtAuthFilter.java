package com.eduardoslg.library.middleware;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eduardoslg.library.utils.JsonResponse;

import java.io.IOException;
import java.util.Set;

public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String SECRET = "sua-chave-super-secreta";

    private static final Set<String> PUBLIC_PATHS = Set.of(
        "/sessions",
        "/users",
        "/users/forgot-password",
        "/users/session"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return PUBLIC_PATHS.contains(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            JsonResponse.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado.");
            return;
        }

        String token = authHeader.replace("Bearer ", "");

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            request.setAttribute("userId", claims.getSubject());
        } catch (Exception e) {
            JsonResponse.writeError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}