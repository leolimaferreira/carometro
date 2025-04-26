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
    public String processarLogin(@RequestParam(
                                         value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout,
                                 @RequestParam(value = "not_found", required = false) String notFound,
                                 Model model) {

        if (error != null) {
            model.addAttribute("error_credentials", true);
            return "login";
        }
        if (logout != null) {
            model.addAttribute("logout_success", true);
            return "login";
        }

        if (notFound != null) {
            model.addAttribute("error_not_found", true);
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login";
    }
}
