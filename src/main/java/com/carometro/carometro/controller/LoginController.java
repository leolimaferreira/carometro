package com.carometro.carometro.controller;

import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public String processarLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<Usuario> usuario = usuarioService.obterPorEmail(username);

        if (usuario.isEmpty()) {
            model.addAttribute("error_not_found", true);
            return "login";
        }

        if (usuario.get().getSenha().equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error_credentials", true);
            return "login";
        }
    }

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login";
    }
}
