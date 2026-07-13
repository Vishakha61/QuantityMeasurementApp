package com.app.quantitymeasurement.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class OAuthController {

    @GetMapping("/user")
    public Map<String, Object> getUser(
            @AuthenticationPrincipal OAuth2User principal
    ) {

        Map<String, Object> user = new LinkedHashMap<>();

        user.put("Name", principal.getAttribute("name"));
        user.put("Email", principal.getAttribute("email"));
        user.put("Picture", principal.getAttribute("picture"));
        user.put("Google ID", principal.getAttribute("sub"));

        return user;
    }
}