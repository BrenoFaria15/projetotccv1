package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.ResultadoExame;

public interface ResultadoExameService {
	ResultadoExame novoResultado(ResultadoExame resultado);
	ResultadoExame atualizar(ResultadoExame resultado);
	void deletar(ResultadoExame resultado);
	List<ResultadoExame> buscarTodos();
	Optional<ResultadoExame> buscarPorId(Long idResultado);

}
