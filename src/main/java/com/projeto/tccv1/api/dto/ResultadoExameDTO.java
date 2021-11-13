package com.projeto.tccv1.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.tccv1.model.entity.Exame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoExameDTO {
	private long id_resultado;
	private long id_exame;
	private long id_paciente;
	private String resultado;

}
