package com.projeto.tccv1.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.ResultadoExame;
import com.projeto.tccv1.model.repository.ResultadoExameRepository;
import com.projeto.tccv1.service.ResultadoExameService;

@Service
public class ResultadoExameServiceImpl implements ResultadoExameService{
	
	private ResultadoExameRepository repository;
	
	public  ResultadoExameServiceImpl(ResultadoExameRepository repository) {
		this.repository=repository;
	}

	@Override
	public ResultadoExame novoResultado(ResultadoExame resultado) {
		
		return repository.save(resultado);
	}

	@Override
	public ResultadoExame atualizar(ResultadoExame resultado) {
		Objects.requireNonNull(resultado.getId_resultado());
		return repository.save(resultado);
	}

	@Override
	public void deletar(ResultadoExame resultado) {
		Objects.requireNonNull(resultado.getId_resultado());
		repository.delete(resultado);
	}

	@Override
	public List<ResultadoExame> buscarTodos() {
		
		return repository.findAll();
	}

	@Override
	public Optional<ResultadoExame> buscarPorId(Long idResultado) {
		
		return repository.findById(idResultado);
	}

	@Override
	public List<ResultadoExame> buscarPorPaciente(Paciente paciente) {
		
		return repository.findByPaciente(paciente);
	}

}
