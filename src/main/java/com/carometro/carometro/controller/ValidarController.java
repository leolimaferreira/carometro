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
public class ValidarController {

    private final PostagemService postagemService;
    private final UsuarioService usuarioService;

    @PostMapping("/validar/{id}")
    public String validar(@PathVariable UUID id, Principal principal) {

        String email = principal.getName();
        Usuario admin = usuarioService.obterPorEmail(email).get();

        Postagem postagem = postagemService.obterPorId(id).get();
        postagem.setValidado(!postagem.isValidado());
        postagem.setAdmin(admin);
        postagemService.salvar(postagem);
        return "redirect:/postagem/exibir/" + id;
    }

    @GetMapping("/validar")
    public String home(Model model) {
        List<Postagem> postagens = postagemService.obterPostagens();
        model.addAttribute("postagens", postagens != null ? postagens : Collections.emptyList());
        return "validar";
    }
}
