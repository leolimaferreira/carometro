package com.carometro.carometro.controller;

import com.carometro.carometro.model.Postagem;
import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.PostagemService;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/postagem")
@RequiredArgsConstructor
public class PostagemController {

    private final PostagemService postagemService;
    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public String cadastroPostagem(
            @RequestParam String curso,
            @RequestParam String anoConclusao,
            @RequestParam String empresa,
            @RequestParam String funcao,
            @RequestParam String tempoTrabalho,
            @RequestParam(required = false) String linkedin,
            @RequestParam(required = false) String github,
            @RequestParam String comentarioFatec,
            @RequestParam(required = false) String comentarioLivre,
            @RequestParam(required = false) String fotoUrl,
            Principal principal

    ) {
        String email = principal.getName();
        Usuario usuario = usuarioService.obterPorEmail(email).get();

        Postagem postagem = new Postagem();
        postagem.setUsuario(usuario);
        postagem.setCurso(curso);
        postagem.setAnoConclusao(anoConclusao);
        postagem.setEmpresa(empresa);
        postagem.setFuncao(funcao);
        postagem.setTempoTrabalho(tempoTrabalho);
        postagem.setLinkedin(linkedin);
        postagem.setGithub(github);
        postagem.setComentarioFatec(comentarioFatec);
        postagem.setComentarioLivre(comentarioLivre);
        postagem.setFotoUrl(fotoUrl);
        postagemService.salvar(postagem);

        return "redirect:/home";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPublicacao(
            @PathVariable UUID id,
            @RequestParam String empresa,
            @RequestParam String funcao,
            @RequestParam String tempoTrabalho,
            @RequestParam(required = false) String linkedin,
            @RequestParam(required = false) String github,
            @RequestParam(required = false) String fotoUrl,
            Principal principal
    ) {

        Postagem postagem = postagemService.obterPorId(id).get();
        postagem.setEmpresa(empresa);
        postagem.setFuncao(funcao);
        postagem.setTempoTrabalho(tempoTrabalho);
        postagem.setLinkedin(linkedin);
        postagem.setGithub(github);
        postagem.setFotoUrl(fotoUrl);
        postagem.setHabilitado(false);
        postagem.setValidado(false);
        postagemService.salvar(postagem);

        return "redirect:/home";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("postagem", new Postagem());
        return "cadastro-postagem";
    }

    @GetMapping("/exibir/{id}")
    public String mostrarPostagem(Model model, @PathVariable UUID id) {
        Postagem postagem = postagemService.obterPorId(id).get();
        Usuario usuario = postagem.getUsuario();
        model.addAttribute("postagem", postagem);
        model.addAttribute("usuario", usuario);
        return "postagem";
    }

    @GetMapping("/atualizacao/{id}")
    public String exibirFormularioAtualizacao(Model model, @PathVariable UUID id) {
        Postagem postagem = postagemService.obterPorId(id).get();
        Usuario usuario = postagem.getUsuario();
        model.addAttribute("postagem", postagem);
        model.addAttribute("usuario", usuario);
        return "atualizacao-postagem";
    }
}
