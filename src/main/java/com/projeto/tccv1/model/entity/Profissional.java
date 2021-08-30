package com.projeto.tccv1.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="profissional")
@Builder
@Data


public class Profissional 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_profissional;
	
	@Column(unique = true,name = "cns",nullable = false)
	private String cns;
	
	@Column(unique = true,name = "cpf",nullable = false)
	private String cpf;
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
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
	
	@Column(unique = true,name = "rg")
	private String rg;
	
	@Column(name = "celular",nullable = false)
	private String celular;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "especialidade",nullable = false)
	private String especialidade;
	
	@Column(name = "nome_conselho")
	private String nome_conselho;
	
	@Column(name = "cd_conselho")
	private String cd_conselho;
	
	@Column(name = "data_nascimento",nullable = false)
	private Date data_nascimento;
	
	@Column(name = "flg_ativo",nullable = false)
	private boolean flg_ativo;

	
	
	
	
  

	

}
