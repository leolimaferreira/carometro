package com.carometro.carometro.controller;

import com.carometro.carometro.model.Postagem;
import com.carometro.carometro.service.PostagemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostagemService postagemService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model) {

        if (principal != null) {
            String email = principal.getName();
            boolean usuarioTemPostagem = postagemService.usuarioTemPostagem(email);
            model.addAttribute("usuarioTemPostagem", usuarioTemPostagem);
        }

        List<Postagem> postagens = postagemService.obterPostagensValidasEHabilitadas();
        model.addAttribute("postagens", postagens != null ? postagens : Collections.emptyList());
        return "home";
    }

    @PostMapping("/home")
    public String homeAsGuest(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/home";
    }
}
