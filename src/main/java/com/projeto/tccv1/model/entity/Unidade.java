package com.projeto.tccv1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="unidade")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unidade {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_unidade;
	
	@Column(unique = true,name = "cnes" )
	private String cnes;
	
	@Column(name = "razao_social",nullable = false)
	private String razaoSocial;
	
	@Column(name = "logradouro",nullable = false)
	private String logradouro;
	
	@Column(name = "numero",nullable = false)
	private String numero;
	
	@Column(name = "cep",nullable = false)
	private String cep;
	
	@Column(name = "bairro",nullable = false)
	private String bairro;
	
	@Column(name = "complemento",nullable = false)
	private String complemento;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "telefone")
	private String telefone;
	
	
	
	
	
}
