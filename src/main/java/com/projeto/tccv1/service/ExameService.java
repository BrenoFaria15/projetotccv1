package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Exame;

public interface ExameService {
	Exame novoExame(Exame exame);
	Exame atualizar(Exame exame);
	void deletar(Exame exame);
	List<Exame> buscarTodos();
	Optional<Exame> buscarPorId(Long idExame);
}
