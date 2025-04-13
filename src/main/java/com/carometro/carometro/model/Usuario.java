package com.carometro.carometro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "usuario")
    private Postagem postagemCriada;

    @OneToMany(mappedBy = "coordenador")
    private List<Postagem> postagensHabilitadas;

    @OneToMany(mappedBy = "admin")
    private List<Postagem> postagensValidadas;

}