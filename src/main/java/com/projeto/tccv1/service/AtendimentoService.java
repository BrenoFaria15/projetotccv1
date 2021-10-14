package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Atendimento;

public interface AtendimentoService {
	Atendimento  salvar(Atendimento atendimento);
	Atendimento atualizar(Atendimento atendimento);
	void deletar(Atendimento atendimento);
	List<Atendimento> buscar(Atendimento AtendimentoFiltro);
	Optional<Atendimento> buscarPorId(Long idAtendimento);
	

}
