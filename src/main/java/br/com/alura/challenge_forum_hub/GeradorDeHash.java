package br.com.alura.challenge_forum_hub;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeHash {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}
