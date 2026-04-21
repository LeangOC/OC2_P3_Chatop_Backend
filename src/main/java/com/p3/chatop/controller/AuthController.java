package com.p3.chatop.controller;

import com.p3.chatop.config.JwtService;
import com.p3.chatop.dto.LoginDto;
import com.p3.chatop.dto.RegisterDto;
import com.p3.chatop.entity.User;
import com.p3.chatop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {

        if (dto.getEmail() == null || dto.getPassword() == null || dto.getName() == null) {
            return ResponseEntity.badRequest().body("{}");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userService.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        Optional<User> userOpt = userService.findByEmail(dto.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("message", "error"));
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "error"));
        }

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow();

        return ResponseEntity.ok(user);
    }
}