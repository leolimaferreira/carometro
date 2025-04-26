package com.carometro.carometro.service;

import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Usuario> obterPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public void salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obterPorId(UUID id) {
        return usuarioRepository.findById(id);
    }
}
