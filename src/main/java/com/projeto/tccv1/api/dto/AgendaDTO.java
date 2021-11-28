package com.projeto.tccv1.api.dto;

import java.sql.Time;
import java.sql.Timestamp;
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
public class AgendaDTO {
	private long profissional;
	private long unidade;
	private long paciente;
	private long usuario;
	private boolean flg_presente;
	

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
	private String hora;
	
	
	
	
}
