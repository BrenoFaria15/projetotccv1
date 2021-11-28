package com.projeto.tccv1.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.entity.Paciente;

public interface AgendaService {
	 Optional<Agenda> buscarPorId(Long idAgenda);
	 Agenda salvar(Agenda agenda);
	 Agenda atualizar(Agenda agenda);
	 void deletar(Agenda agenda);
	 List<Agenda>buscarPorData(LocalDate data);
	 void atualizarPresenca(Agenda agenda);
	 List<Agenda>buscarPorPaciente(Paciente paciente);
	 

}
