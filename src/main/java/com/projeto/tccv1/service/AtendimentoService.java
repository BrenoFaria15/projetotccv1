package com.projeto.tccv1.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;

public interface AtendimentoService {
	Atendimento  salvar(Atendimento atendimento);
	Atendimento atualizar(Atendimento atendimento);
	void deletar(Atendimento atendimento);
	List<Atendimento> buscar(Atendimento AtendimentoFiltro);
	Optional<Atendimento> buscarPorId(Long idAtendimento);
	List<Atendimento> buscarPaciente(Paciente paciente);
	List<Atendimento> buscarAtendimento(Paciente paciente,Profissional profissional,LocalDate data);

}
