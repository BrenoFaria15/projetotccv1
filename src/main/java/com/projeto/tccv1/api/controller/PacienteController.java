package com.projeto.tccv1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.PacienteDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.service.PacienteService;

	@RestController
	@RequestMapping("/api/pacientes")
public class PacienteController {

	private PacienteService service;
	
	@Autowired
	public void PacienteResource(PacienteService service) {
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity salvarPaciente(@RequestBody PacienteDTO dto) {
		
	
		Paciente paciente = Paciente.builder().
				bairro(dto.getBairro()).
				celular(dto.getCelular()).
				cep(dto.getCep()).
				cns(dto.getCns())
				.complemento(dto.getComplemento()).
				convenio(dto.getConvenio()).
				cpf(dto.getCpf()).
				data_nascimento(dto.getData_nascimento()).
				data_obito(dto.getData_obito()).
				flg_ativo(dto.isFlg_ativo()).
				flg_obito(dto.isFlg_obito()).
				logradouro(dto.getLogradouro()).
				municipio(dto.getMunicipio()).
				nome(dto.getNome()).
				nome_mae(dto.getNome_mae()).
				nome_pai(dto.getNome_pai()).
				numero(dto.getNumero()).
				rg(dto.getRg()).build();	
		
		try {
			Paciente pacienteSalvo = service.novoPaciente(paciente);
			return new ResponseEntity(pacienteSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
