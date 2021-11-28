package com.projeto.tccv1.model.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

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
@Table(name="agenda")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_agenda;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "data",nullable = false)
	private LocalDate data;
	
	@Column(name = "hora",nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
	private String hora;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_profissional")
	private Profissional profissional;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name="flg_presente")
	private boolean flg_presente;
	
	
	
	

}
