package com.carometro.carometro.controller.mappers;

import com.carometro.carometro.model.Usuario;
import com.carometro.carometro.model.dto.UsuarioCadastro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioCadastro dto);

    UsuarioCadastro toDto(Usuario usuario);
}
