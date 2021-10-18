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
@Table(name="tipo_atendimento")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TipoAtendimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_tipo_atendimento;

	@Column(unique = true,name = "tipo_nome",nullable = false)
	private String tipoNome;
	
	@Column(name = "descricao")
	private String Descricao;
	
	

}
