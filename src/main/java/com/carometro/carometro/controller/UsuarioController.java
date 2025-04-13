package com.carometro.carometro.controller;

import com.carometro.carometro.controller.mappers.UsuarioMapper;
import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.model.dto.UsuarioCadastro;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping
    public String cadastroUsuario(UsuarioCadastro dto) {
        Usuario usuario = mapper.toEntity(dto);
        usuarioService.salvar(usuario);
        return "redirect:/usuarios/login";
    }

    @GetMapping("/form")
    public String exibirFormulario(org.springframework.ui.Model model) {
        model.addAttribute("usuario", new UsuarioCadastro("", "", "", ""));
        return "cadastro";
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Usuario usuario = usuarioService.obterPorEmail(username).get();

        if (usuario.getSenha().equals(password)) {
            return "redirect:/usuarios/home";
        } else {
            model.addAttribute("error", "true");
            return "login";
        }
    }

    @GetMapping("/login")
    public String mostrarLoginForm() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
