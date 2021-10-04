package com.projeto.tccv1.api.dto;



import lombok.Builder;
import lombok.Data;

@Builder
@Data
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
}
