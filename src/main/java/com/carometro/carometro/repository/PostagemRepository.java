package com.carometro.carometro.repository;

import com.carometro.carometro.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostagemRepository extends JpaRepository<Postagem, UUID> {
    List<Postagem> findByValidadoTrueAndHabilitadoTrue();

    boolean existsByUsuarioEmail(String email);
}
