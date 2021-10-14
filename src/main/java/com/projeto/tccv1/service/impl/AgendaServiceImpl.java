package com.projeto.tccv1.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.repository.AgendaRepository;
import com.projeto.tccv1.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService{
	
	private AgendaRepository repository;
	
	
	public AgendaServiceImpl(AgendaRepository repository) {
		super();
		this.repository=repository;
	}
	

	@Override
	public Optional<Agenda> buscarPorId(Long idAgenda) {
		
		
		return repository.findById(idAgenda);
	}

}
