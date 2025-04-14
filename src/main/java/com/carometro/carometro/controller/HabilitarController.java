package com.carometro.carometro.controller;

import com.carometro.carometro.model.Postagem;
import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.PostagemService;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HabilitarController {

    private final PostagemService postagemService;
    private final UsuarioService usuarioService;

    @PostMapping("/habilitar/{id}")
    public String habilitar(@PathVariable UUID id, Principal principal) {

        String email = principal.getName();
        Usuario coordenador = usuarioService.obterPorEmail(email).get();

        Postagem postagem = postagemService.obterPorId(id).get();
        postagem.setHabilitado(!postagem.isHabilitado());
        postagem.setCoordenador(coordenador);
        postagemService.salvar(postagem);
        return "redirect:/postagem/exibir/" + id;
    }

    @GetMapping("/habilitar")
    public String home(Model model) {
        List<Postagem> postagens = postagemService.obterPostagens();
        model.addAttribute("postagens", postagens != null ? postagens : Collections.emptyList());
        return "habilitar";
    }
}
