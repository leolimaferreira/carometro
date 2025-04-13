package com.carometro.carometro.controller;

import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public String processarLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Usuario usuario = usuarioService.obterPorEmail(username).get();

        if (usuario.getSenha().equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "true");
            return "login";
        }
    }

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login";
    }
}
