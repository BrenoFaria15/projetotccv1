package com.projeto.tccv1.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_usuario;
	
	
	@Column(unique = true,name = "nome_usuario",nullable = false)
	private String nome;
	
	@Column(name = "senha",nullable = false)
	private String senha;
	
	@Column(name = "tipo_usuario",nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	
}