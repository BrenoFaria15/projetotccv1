package com.projeto.tccv1.api.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
	private long id_paciente;
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
	private String nomePai;
	private String nomeMae;
	private String municipio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	private boolean flgAtivo;
	private boolean flgObito;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataObito;
	
	private String convenio;

}
