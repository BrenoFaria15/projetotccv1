package com.projeto.tccv1.service;

import java.util.Optional;

import com.projeto.tccv1.model.entity.Agenda;

public interface AgendaService {
	 Optional<Agenda> buscarPorId(Long idAgenda);

}
