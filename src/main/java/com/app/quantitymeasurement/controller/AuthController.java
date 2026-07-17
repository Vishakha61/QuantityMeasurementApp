package com.app.quantitymeasurement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/api/auth/login")
    public ResponseEntity<Void> login() {

        return ResponseEntity.status(302)
                .header(
                        "Location",
                        "/oauth2/authorization/google"
                )
                .build();
    }
}