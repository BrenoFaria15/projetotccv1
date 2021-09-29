package com.projeto.tccv1.service;

import java.util.Optional;

import com.projeto.tccv1.model.entity.Paciente;

public interface PacienteService {
	Paciente novoPaciente(Paciente paciente);
	void validarCPF(String cpf);
	void validarRG(String RG);
	void validarCNS(String cns);
	Optional<Paciente> finByNome(String nome);
	Optional<Paciente> findByCpf(String cpf);

}
