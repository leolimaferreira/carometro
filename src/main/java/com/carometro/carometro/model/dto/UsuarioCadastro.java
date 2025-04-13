package com.carometro.carometro.model.dto;

public record UsuarioCadastro(
        String nome,
        String email,
        String senha,
        String role
) {
}
