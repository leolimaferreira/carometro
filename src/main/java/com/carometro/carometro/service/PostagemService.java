package com.carometro.carometro.service;

import com.carometro.carometro.model.Postagem;
import com.carometro.carometro.repository.PostagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostagemService {

    private final PostagemRepository postagemRepository;

    public Optional<Postagem> obterPorId(UUID id) {
        return postagemRepository.findById(id);
    }

    public List<Postagem> obterPostagens() {
        return postagemRepository.findAll();
    }

    public List<Postagem> obterPostagensValidasEHabilitadas() {
        return postagemRepository.findByValidadoTrueAndHabilitadoTrue();
    }

    public void salvar(Postagem postagem) {
        postagemRepository.save(postagem);
    }

    public boolean usuarioTemPostagem(String email) {
        return postagemRepository.existsByUsuarioEmail(email);
    }
}
