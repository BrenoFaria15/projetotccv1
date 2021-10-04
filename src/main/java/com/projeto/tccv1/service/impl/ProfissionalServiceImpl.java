package com.projeto.tccv1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.repository.ProfissionalRepository;
import com.projeto.tccv1.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService{
	
	@Autowired
	private ProfissionalRepository repository;
	
	
	public ProfissionalServiceImpl(ProfissionalRepository repository) {
		super();
		this.repository = repository;
	}
	

	@Override
	public Profissional novoProfissional(Profissional profissional) {
		validarCPF(profissional.getCpf());
		validarRG(profissional.getRg());
		validarCNS(profissional.getCns());
		return repository.save(profissional);
	}

	@Override
	public void validarCPF(String cpf) {
		boolean existe=repository.existsByCpf(cpf);
		if(existe) {
			throw new RegraNegocioException("Ja existe um profissional com esse cpf cadastrado");
		}
		
	}

	@Override
	public void validarRG(String RG) {
		boolean existe=repository.existsByRg(RG);
		if(existe) {
			throw new RegraNegocioException("Ja existe um profissional com esse rg cadastrado");
		}
		
	}

	@Override
	public void validarCNS(String cns) {
		boolean existe=repository.existsByCns(cns);
		if(existe) {
			throw new RegraNegocioException("Ja existe um profissional com esse cartão sus cadastrado");
		} 
		
	}

}
