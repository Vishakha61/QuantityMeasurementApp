package com.app.quantitymeasurement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Operation(summary = "Redirect to Google Login")
    @GetMapping("/api/auth/login")
    public String login() {
        return "Redirect to /oauth2/authorization/google";
    }

    @Operation(summary = "Google OAuth Callback")
    @GetMapping("/api/auth/callback/google")
    public Map<String, Object> callback(
            @AuthenticationPrincipal OAuth2User user
    ) {

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("name", user.getAttribute("name"));
        response.put("email", user.getAttribute("email"));
        response.put("picture", user.getAttribute("picture"));

        return response;
    }

    @Operation(summary = "Protected Profile API")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/api/profile")
    public String profile() {
        return "JWT Authentication Successful";
    }

    @Operation(summary = "Test API")
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}