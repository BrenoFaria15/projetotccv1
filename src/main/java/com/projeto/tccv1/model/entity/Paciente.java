package com.projeto.tccv1.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="paciente")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
	
	/*Paciente[{id_paciente,cns,cpf,nome,data_nascimento,raça/cor,fone_celular,fone_residencial,
nome_pai,nome_mãe,estado_civil,escolaridade,logradouro,numero,cep,bairro,complemento,FLG_Ativo,
FLG_Obito,data_obito,certidão de obito, convenio,sexo}]*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_paciente;
	
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
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(unique = true,name = "rg")
	private String rg;
	
	@Column(name = "celular",nullable = false)
	private String celular;
	
	@Column(name = "nome_pai")
	private String nome_pai;
	
	@Column(name = "nome_mae")
	private String nome_mae;
	
	@Column(name = "municipio")
	private String municipio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento",nullable = false)
	private Date data_nascimento;
	
	@Column(name = "flg_ativo")
	private boolean flg_ativo;

	@Column(name = "flg_obito")
	private boolean flg_obito;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "data_obito")
	private Date data_obito;
	
	@Column(name = "convenio")
	private String convenio;
	
	

}
