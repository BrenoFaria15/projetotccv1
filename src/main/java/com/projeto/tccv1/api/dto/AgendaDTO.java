package com.projeto.tccv1.api.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AgendaDTO {
	private long atendimento;
	private long profissional;
	private long unidade;
	private long paciente;
	private long usuario;
	private boolean flg_atendido;
	

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date data;
	private Time hora;
}
