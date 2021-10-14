package com.projeto.tccv1.service;

import java.util.Optional;

import com.projeto.tccv1.model.entity.Unidade;

public interface UnidadeService {
	Unidade novaUnidade(Unidade unidade);
	void validarCnes(String cnes);
	Optional<Unidade> buscarPorId(Long idUnidade);

}
