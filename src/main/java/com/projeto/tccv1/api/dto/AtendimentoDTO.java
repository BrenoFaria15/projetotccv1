package com.projeto.tccv1.api.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.model.entity.Usuario;
import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDTO {

	private long id_atendimento;
	private long profissional;
	private long unidade;
	private long paciente;
	private boolean flg_atendido;
	private long usuario;
	private long tipoatendimento;
	private long id_agenda;
	private float peso;
	private  int altura;
	private float temperatura;
	private String glicemia;
	private String bpm;
	private String saturacao;
	private float imc;
	private String avaliacao;
	private String entrevistaClinica;
	private String cid;
	private  int pressao1;
	private  int pressao2;
	private String hipotese;
	

	
	private String horaInicio;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
	private String horaFim;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate data;
}
