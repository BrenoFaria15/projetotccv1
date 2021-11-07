package com.projeto.tccv1.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.model.repository.UnidadeRepository;
import com.projeto.tccv1.service.UnidadeService;

@Service
public class UnidadeServiceImpl implements UnidadeService {
	
private UnidadeRepository repository;
	
	
	public UnidadeServiceImpl(UnidadeRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Unidade novaUnidade(Unidade unidade) {
		validarCnes(unidade.getCnes());
		return repository.save(unidade);
	}

	@Override
	public void validarCnes(String cnes) {
		boolean existe=repository.existsByCnes(cnes);
		if(existe) {
			throw new RegraNegocioException("Ja existe uma unidade com esse cnes cadastrado");
		}
		
	}

	@Override
	public Optional<Unidade> buscarPorId(Long idUnidade) {
		
		return repository.findById(idUnidade);
	}

	@Override
	@Transactional
	public void deletar(Unidade unidade) {
		Objects.requireNonNull(unidade.getId_unidade());
		repository.delete(unidade);
		
	}

	@Override
	@Transactional
	public Unidade atualizar(Unidade unidade) {
		Objects.requireNonNull(unidade.getId_unidade());
		return repository.save(unidade);
	}

	@Override
	public List<Unidade> buscarTodos() {
		
		return repository.findAll();
	}

}
