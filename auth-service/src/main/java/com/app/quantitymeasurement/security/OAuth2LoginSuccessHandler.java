package com.app.quantitymeasurement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User user =
                (OAuth2User) authentication.getPrincipal();

        String email =
                user.getAttribute("email");

        String token =
                jwtUtil.generateToken(email);

        response.setContentType("text/html");

        response.getWriter().write("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Login Successful</title>
                </head>
                <body>
                
                <script>
                
                window.location.href = "http://localhost:5173/oauth-success?token=%s";
                
                </script>
                
                </body>
                </html>
                """.formatted(token));
    }}