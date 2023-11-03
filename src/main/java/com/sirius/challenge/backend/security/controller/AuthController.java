package com.sirius.challenge.backend.security.controller;

import com.sirius.challenge.backend.documentation.IAuthController;
import com.sirius.challenge.backend.security.models.AuthRequest;
import com.sirius.challenge.backend.security.models.AuthResponse;
import com.sirius.challenge.backend.security.models.RegisterRequest;
import com.sirius.challenge.backend.security.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok().body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok().body(authService.authenticate(request));
    }

}
