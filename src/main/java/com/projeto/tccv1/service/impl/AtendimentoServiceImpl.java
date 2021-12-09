package com.projeto.tccv1.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.Unidade;
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

	@Override
	public List<Atendimento> buscarPaciente(Paciente paciente) {
		
		return repository.findByPaciente(paciente);
	}

	@Override
	public List<Atendimento> buscarAtendimento(Paciente paciente, Profissional profissional, LocalDate data,
			Unidade unidade) {
		return repository.findByPacienteAndProfissionalAndDataAndUnidade(paciente, profissional, data,unidade);
	}

	@Override
	public List<Atendimento> buscarAtendimentoPaciente(Paciente paciente, LocalDate data, Unidade unidade) {
		
		return repository.findByPacienteAndDataAndUnidade(paciente, data, unidade);
	}

	@Override
	public List<Atendimento> buscarAtendimentoData(LocalDate data, Unidade unidade) {
		
		return repository.findByDataAndUnidade(data, unidade);
	}

	@Override
	public List<Atendimento> buscarAtendimentoProfissional(Profissional profissional, LocalDate data, Unidade unidade) {
		
		return repository.findByProfissionalAndDataAndUnidade(profissional, data, unidade);
	}

	

}
