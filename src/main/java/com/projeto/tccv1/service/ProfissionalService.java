package com.projeto.tccv1.service;

import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Profissional;


public interface ProfissionalService {
	Profissional novoProfissional(Profissional profissional);
	void validarCPF(String cpf);
	void validarRG(String RG);
	void validarCNS(String cns);
	Optional<Profissional> buscarPorId(Long idProfissional);
	Profissional atualizar(Profissional profissional);
	void deletar(Profissional profissional);
	List<Profissional>buscarTodos();
}

