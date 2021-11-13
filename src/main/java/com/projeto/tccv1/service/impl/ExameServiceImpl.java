package com.projeto.tccv1.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.model.entity.Exame;
import com.projeto.tccv1.model.repository.ExameRepository;
import com.projeto.tccv1.service.ExameService;

@Service
public class ExameServiceImpl implements ExameService{
	
	private ExameRepository repository;
	
	public  ExameServiceImpl(ExameRepository repository) {
		this.repository=repository;
	}

	@Override
	@Transactional
	public Exame novoExame(Exame exame) {
		
		return repository.save(exame);
	}

	@Override
	@Transactional
	public Exame atualizar(Exame exame) {
		Objects.requireNonNull(exame.getId_exame());
		return repository.save(exame);
	}

	@Override
	@Transactional
	public void deletar(Exame exame) {
		Objects.requireNonNull(exame.getId_exame());
		repository.delete(exame);
	}

	@Override
	public List<Exame> buscarTodos() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Exame> buscarPorId(Long idExame) {
	
		return repository.findById(idExame);
	}

}
