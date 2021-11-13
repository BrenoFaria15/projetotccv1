package com.projeto.tccv1.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="exame")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_exame;
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
	@Column(name = "cod_sus",nullable = false)
	private String codSus;
	
	@Column(name = "descricao")
	private String descricao;

}
