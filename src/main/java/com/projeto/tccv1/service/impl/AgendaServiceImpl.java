package com.projeto.tccv1.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	@Override
	@Transactional
	public Agenda salvar(Agenda agenda) {
		
		return repository.save(agenda);
	}


	@Override
	@Transactional
	public Agenda atualizar(Agenda agenda) {
		Objects.requireNonNull(agenda.getId_agenda());
		return repository.save(agenda);
	}


	@Override
	@Transactional
	public void deletar(Agenda agenda) {
		Objects.requireNonNull(agenda.getId_agenda());
		repository.delete(agenda);
		
	}

}
