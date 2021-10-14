package com.projeto.tccv1.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.model.repository.TipoAtendimentoRepository;
import com.projeto.tccv1.service.TipoAtendimentoService;

@Service
public class TipoAtendimentoServiceImpl implements TipoAtendimentoService {
	
	@Autowired
	private TipoAtendimentoRepository repository;
	
	
	public TipoAtendimentoServiceImpl(TipoAtendimentoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public TipoAtendimento novoTipoAtendimento(TipoAtendimento TipoAtend) {
		ValidarTipoAtendimento(TipoAtend.getTipoNome());
		return repository.save(TipoAtend);
	}

	@Override
	public void ValidarTipoAtendimento(String TipoAtendimento) {
		boolean existe=repository.existsBytipoNome(TipoAtendimento);
		if(existe) {
			throw new RegraNegocioException("Ja existe um tipo de atendimento com esse nome cadastrado");
		}
		
	}

	@Override
	public Optional<TipoAtendimento> buscarPorId(Long idTipoAtendimento) {
		
		return repository.findById(idTipoAtendimento);
	}
	

}
