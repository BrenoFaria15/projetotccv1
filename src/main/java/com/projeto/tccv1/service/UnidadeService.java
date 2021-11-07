package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Unidade;

public interface UnidadeService {
	Unidade novaUnidade(Unidade unidade);
	void validarCnes(String cnes);
	Optional<Unidade> buscarPorId(Long idUnidade);
	void deletar(Unidade unidade);
	Unidade atualizar(Unidade unidade);
	List<Unidade>buscarTodos();

}
