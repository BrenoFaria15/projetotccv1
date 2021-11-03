package com.projeto.tccv1.api.dto;


import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private String nome;
	private String senha;
	private TipoUsuario tipoUsuario;
}
