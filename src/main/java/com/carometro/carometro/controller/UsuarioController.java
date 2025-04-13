package com.carometro.carometro.controller;

import com.carometro.carometro.controller.mappers.UsuarioMapper;
import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.model.dto.UsuarioCadastro;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping("/cadastrar")
    public String cadastroUsuario(@ModelAttribute("usuario") UsuarioCadastro dto, Model model) {
        if (usuarioService.obterPorEmail(dto.email()).isPresent()) {
            model.addAttribute("error_email_registered", true);
            model.addAttribute("usuario", dto);
            return "cadastro";
        }

        Usuario usuario = mapper.toEntity(dto);
        usuarioService.salvar(usuario);
        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(org.springframework.ui.Model model) {
        model.addAttribute("usuario", new UsuarioCadastro("", "", "", ""));
        return "cadastro";
    }
}
