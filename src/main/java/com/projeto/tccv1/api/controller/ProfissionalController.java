package com.projeto.tccv1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.ProfissionalDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.service.ProfissionalService;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
	
	private ProfissionalService service;
	
	@Autowired
	public void ProfissionalResource(ProfissionalService service) {
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity salvarProfissional(@RequestBody ProfissionalDTO dto) {
		
		Profissional profissional = Profissional.builder().
				bairro(dto.getBairro()).
				cd_conselho(dto.getCd_conselho()).
				celular(dto.getCelular()).
				cep(dto.getCep()).
				cns(dto.getCns()).
				complemento(dto.getComplemento()).
				cpf(dto.getCpf()).
				data_nascimento(dto.getData_nascimento()).
				especialidade(dto.getEspecialidade()).
				flg_ativo(dto.isFlg_ativo()).
				logradouro(dto.getLogradouro()).
				municipio(dto.getMunicipio()).
				nome(dto.getNome()).
				nome_conselho(dto.getNome_conselho()).
				numero(dto.getNumero()).
				rg(dto.getRg()).build();		
		try {
			Profissional profissionalSalvo = service.novoProfissional(profissional);
			return new ResponseEntity(profissionalSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}

}
