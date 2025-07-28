package br.com.alura.challenge_forum_hub.controller;

import br.com.alura.challenge_forum_hub.dto.LoginRequest;
import br.com.alura.challenge_forum_hub.dto.LoginResponse;
import br.com.alura.challenge_forum_hub.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public LoginResponse autenticar(@RequestBody LoginRequest loginRequest) {

        var authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.senha()
        );

        var authResult = authenticationManager.authenticate(authToken);

        var userDetails = (org.springframework.security.core.userdetails.UserDetails) authResult.getPrincipal();

        String token = tokenService.gerarToken(userDetails);

        return new LoginResponse(token, "bearer");
    }

}
