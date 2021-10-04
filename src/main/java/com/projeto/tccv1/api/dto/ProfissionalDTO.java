package com.projeto.tccv1.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProfissionalDTO {
	private String cns;
	private String cpf;
	private String nome;
	private String logradouro;
	private String numero;
	private String cep;
	private String bairro;
	private String complemento;
	private String rg;
	private String celular;
	private String municipio;
	private String especialidade;
	private String nome_conselho;
	private String cd_conselho;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data_nascimento;
	
	private boolean flg_ativo;
}
