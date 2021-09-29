package com.projeto.tccv1.service;

import com.projeto.tccv1.model.entity.TipoAtendimento;

public interface TipoAtendimentoService {

	TipoAtendimento novoTipoAtendimento(TipoAtendimento TipoAtend);
	void ValidarTipoAtendimento(String TipoAtendimento);
}
