package com.carometro.carometro.controller;

import com.carometro.carometro.model.Postagem;
import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.PostagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostagemController {

    private final PostagemService postagemService;

    @GetMapping("/postagem/{id}")
    public String mostrarPostagem(Model model, @PathVariable UUID id) {
        Postagem postagem = postagemService.obterPorId(id).get();
        Usuario usuario = postagem.getUsuario();
        model.addAttribute("postagem", postagem);
        model.addAttribute("usuario", usuario);
        return "postagem";
    }
}
