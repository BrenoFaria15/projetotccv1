package com.projeto.tccv1.model.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="atendimento")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {
	/*id_atendimento,id_profissional,id_paciente,id_tipoatend,data,hora,peso,altura,
	 * pressao1,pressao2,estatura,temperatura,glicemia,bpm,saturacaom,imc,
	 * id_usuario,avaliação,entrevista clinica,cd_cid,cd_motivo*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_atendimento;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_profissional")
	private Profissional profissional;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name="flg_atendido")
	private boolean flg_atendido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_tipo_atendimento")
	private TipoAtendimento tipoatendimento;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_agenda")
	private Agenda agenda;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "altura")
	private  int altura;
	
	@Column(name = "pressao1")
	private  int pressao1;
	
	@Column(name = "pressao2")
	private  int pressao2;
	
	@Column(name = "temperatura")
	private float temperatura;
	
	@Column(name = "glicemia")
	private String glicemia;
	
	
	@Column(name = "bpm")
	private String bpm;
	
	@Column(name = "saturacao")
	private String saturacao;
	
	
	@Column(name = "imc")
	private float imc;
	
	@Column(name = "avaliacao")
	private String avaliacao;
	
	@Column(name = "entrevista_clinica")
	private String entrevistaClinica;
	
	@Column(name = "cid")
	private String cid;
	
	@Column(name = "hipotese")
	private String hipotese;
	
	
	
	@Column(name = "hora_inicio")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
	private String horaInicio;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
	@Column(name = "hora_fim")
	private String horaFim;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data")
	private LocalDate data;
	
	
	
	
	
	
	

}
