package com.projeto.tccv1.api.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.model.entity.Usuario;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AtendimentoDTO {

	private long id_atendimento;
	private long profissional;
	private long unidade;
	private long paciente;
	private boolean flg_atendido;
	private long usuario;
	private long tipoatendimento;
	private long id_agenda;
	private float peso;
	private  int altura;
	private float temperatura;
	private String glicemia;
	private String bpm;
	private String saturacao;
	private float imc;
	private String avaliacao;
	private String entrevistaClinica;
	private String cid;
	private Timestamp horaInicio;
	private Timestamp horaFim;
	private Date data;
}
