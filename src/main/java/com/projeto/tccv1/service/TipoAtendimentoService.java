package com.projeto.tccv1.service;

import java.util.Optional;

import com.projeto.tccv1.model.entity.TipoAtendimento;

public interface TipoAtendimentoService {

	TipoAtendimento novoTipoAtendimento(TipoAtendimento TipoAtend);
	void ValidarTipoAtendimento(String TipoAtendimento);
	Optional<TipoAtendimento> buscarPorId(Long idTipoAtendimento);
}
