package com.projeto.tccv1.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.repository.PacienteRepository;
import com.projeto.tccv1.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	
	@Autowired
	private PacienteRepository repository;
	
	
	public PacienteServiceImpl(PacienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	
	@Override
	@Transactional
	public Paciente novoPaciente(Paciente paciente) {
		
	
		validarCPF(paciente.getCpf());
		validarRG(paciente.getRg());
		validarCNS(paciente.getCns());
		return repository.save(paciente);
		
	}

	@Override
	public void validarCPF(String cpf) {
		boolean existe=repository.existsByCpf(cpf);
		if(existe) {
			throw new RegraNegocioException("Ja existe um pacienete com esse cpf cadastrado");
		}
		
	}

	@Override
	public void validarRG(String RG) {
		boolean existe=repository.existsByRg(RG);
		if(existe) {
			throw new RegraNegocioException("Ja existe um pacienete com esse rg cadastrado");
		}
		
	}

	@Override
	public void validarCNS(String cns) {
		boolean existe=repository.existsByCns(cns);
				if(existe) {
					throw new RegraNegocioException("Ja existe um pacienete com esse cartão sus cadastrado");
				} 
		
	}



	@Override
	public Optional<Paciente> finByNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Optional<Paciente> findByCpf(String cpf) {
		
		return repository.findByCpf(cpf);
	}



	@Override
	public Optional<Paciente> buscarPorId(Long idPaciente) {
		
		return repository.findById(idPaciente);
	}



	@Override
	@Transactional
	public Paciente atualizar(Paciente paciente) {
		Objects.requireNonNull(paciente.getId_paciente());
		return repository.save(paciente);
	}



	@Override
	@Transactional
	public void deletar(Paciente paciente) {
	 Objects.requireNonNull(paciente.getId_paciente());
	 repository.delete(paciente);
	}



	@Override
	public List<Paciente> buscarTodos() {
		
		return repository.findAll();
	}

}
