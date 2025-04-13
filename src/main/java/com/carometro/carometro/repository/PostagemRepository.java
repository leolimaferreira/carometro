package com.carometro.carometro.repository;

import com.carometro.carometro.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostagemRepository extends JpaRepository<Postagem, UUID> {
}
