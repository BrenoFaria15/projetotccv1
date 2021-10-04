package com.projeto.tccv1.api.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoAtendimentoDTO {

	private String tipoNome;
	private String Descricao;
}
