package dev.roberto.memorize.controller;

import dev.roberto.memorize.dto.LoginRequest;
import dev.roberto.memorize.dto.LoginResponse;
import dev.roberto.memorize.dto.UserResponse;
import dev.roberto.memorize.model.User;
import dev.roberto.memorize.repository.UserRepository;
import dev.roberto.memorize.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getIdentifier());
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(loginRequest.getIdentifier());
        }

        if (userOpt.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOpt.get().getPasswordHash())) {
            return ResponseEntity.status(401).body(new ErrorResponse("Invalid credentials"));
        }

        User user = userOpt.get();
        String token = tokenProvider.generateToken(user.getUsername(), user.getId());

        return ResponseEntity.ok(new LoginResponse(token, new UserResponse(user)));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof User)) {
            return ResponseEntity.status(401).build();
        }
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new UserResponse(user));
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
