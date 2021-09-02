package com.projeto.tccv1.model.entity;

import java.sql.Time;
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
@Table(name="agenda")
@Builder
@Data
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_agenda;
	
	@Column(name = "data",nullable = false)
	private Date data;
	
	@Column(name = "hora",nullable = false)
	private Time hora;
	
	@ManyToOne
	@JoinColumn(name="id_profissional")
	private Profissional profissional;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@OneToOne
	@JoinColumn(name="id_atendimento")
	private Atendimento atendimento;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name="flg_atendido")
	private boolean flg_atendido;
	
	
	
	

}
