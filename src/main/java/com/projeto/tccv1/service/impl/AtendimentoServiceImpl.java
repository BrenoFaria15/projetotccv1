package com.projeto.tccv1.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.repository.AtendimentoRepository;
import com.projeto.tccv1.service.AtendimentoService;

@Service
public class AtendimentoServiceImpl implements AtendimentoService{

	private AtendimentoRepository repository;
	
	public AtendimentoServiceImpl(AtendimentoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public Atendimento salvar(Atendimento atendimento) {
	
		return repository.save(atendimento);
	}

	@Override
	@Transactional
	public Atendimento atualizar(Atendimento atendimento) {
		Objects.requireNonNull(atendimento.getId_atendimento());
		return repository.save(atendimento);
	}

	@Override
	@Transactional
	public void deletar(Atendimento atendimento) {
		Objects.requireNonNull(atendimento.getId_atendimento());
		repository.delete(atendimento);
		
	}

	@Override
	@Transactional
	public List<Atendimento> buscar(Atendimento atendimentoFiltro) {
		Example example = Example.of(atendimentoFiltro, 
				ExampleMatcher.matchingAny()
				.withIgnoreCase()
				);
		return repository.findAll(example);
	}
	

	@Override
	public Optional<Atendimento> buscarPorId(Long idAtendimento) {
		
		return repository.findById(idAtendimento);
	}

}
