package com.projeto.tccv1.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="resultado_exame")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoExame {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_resultado;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_exame")
	private Exame exame;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_paciente")
	private Paciente paciente;
	
	@Column(name = "resultado")
	private String resultado;

}
