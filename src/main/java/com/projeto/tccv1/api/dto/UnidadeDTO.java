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
public class UnidadeDTO {

	private String cnes;
	private String razaoSocial;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String complemento;
	private String municipio;
	private String telefone;
	private String nomeFantasia;
}

