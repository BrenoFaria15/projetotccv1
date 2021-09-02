package com.projeto.tccv1.model.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="atendimento")
@Builder
@Data
public class Atendimento {
	/*id_atendimento,id_profissional,id_paciente,id_tipoatend,data,hora,peso,altura,
	 * pressao1,pressao2,estatura,temperatura,glicemia,bpm,saturacaom,imc,
	 * id_usuario,avaliação,entrevista clinica,cd_cid,cd_motivo*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_atendimento;
	
	
	@ManyToOne
	@JoinColumn(name="id_profissional")
	private Profissional profissional;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name="flg_atendido")
	private boolean flg_atendido;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name="id_tipo_atendimento")
	private TipoAtendimento tipoatendimento;
	
	@OneToOne
	@JoinColumn(name="id_agenda")
	private Agenda agenda;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "altura")
	private  int altura;
	
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
	
	@Column(name = "hora_inicio")
	private Timestamp horaInicio;
	
	@Column(name = "hora_fim")
	private Timestamp horaFim;
	
	@Column(name = "data")
	private Date data;
	
	
	
	
	
	
	

}
