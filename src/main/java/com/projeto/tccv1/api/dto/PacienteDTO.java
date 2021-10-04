package com.projeto.tccv1.api.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PacienteDTO {
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
	private String nome_pai;
	private String nome_mae;
	private String municipio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data_nascimento;
	
	private boolean flg_ativo;
	private boolean flg_obito;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data_obito;
	
	private String convenio;

}
