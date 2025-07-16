package br.com.alura.challenge_forum_hub.controller;

import br.com.alura.challenge_forum_hub.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.email(), login.senha())
        );

        User user = (User) auth.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    public record LoginRequest(String email, String senha) {}
}
