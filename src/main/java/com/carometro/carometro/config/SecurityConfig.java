package com.carometro.carometro.config;

import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "home",
                                "/",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/cadastro",
                                "/cadastrar",
                                "/postagem/exibir/**").permitAll()
                        .requestMatchers("/postagem/cadastro").hasRole("EX_ALUNO")
                        .requestMatchers("/postagem/cadastrar").hasRole("EX_ALUNO")
                        .requestMatchers("/postagem/atualizacao").hasRole("EX_ALUNO")
                        .requestMatchers("/postagem/atualizar/**").hasRole("EX_ALUNO")
                        .requestMatchers("/habilitar/**").hasRole("COORDENADOR")
                        .requestMatchers("validar/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureHandler((request, response, exception) -> {
                            String email = request.getParameter("username");
                            boolean userNotFound = !usuarioService.obterPorEmail(email).isPresent();

                            if (userNotFound) {
                                response.sendRedirect("/login?not_found=true");
                            } else {
                                response.sendRedirect("/login?error=true");
                            }
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioService.obterPorEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            return User.builder()
                    .username(usuario.getEmail())
                    .password(usuario.getSenha())
                    .roles(usuario.getRole().toString())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
