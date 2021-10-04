package com.projeto.tccv1.api.dto;


import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioDTO {

	private String nome;
	private String senha;
	private TipoUsuario tipoUsuario;
}
