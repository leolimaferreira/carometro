package com.carometro.carometro.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "postagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String curso;
    private String anoConclusao;
    private String empresa;
    private String funcao;
    private String tempoTrabalho;

    private String fotoUrl;
    private String linkedin;
    private String github;

    private String comentarioFatec;
    private String comentarioLivre;

    private boolean habilitado;
    private boolean validado;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "coordenador_id")
    private Usuario coordenador;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Usuario admin;

}

