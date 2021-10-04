package com.projeto.tccv1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.UnidadeDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.service.UnidadeService;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {
	
	private UnidadeService service;
	
	@Autowired
	public void UnidadeResource(UnidadeService service) {
		this.service=service;
	}
	
	@PostMapping
	public ResponseEntity salvarUnidade(@RequestBody UnidadeDTO dto) {
		Unidade unidade = Unidade.builder().
				bairro(dto.getBairro()).
				cep(dto.getCep()).
				cnes(dto.getCnes()).
				complemento(dto.getComplemento()).
				logradouro(dto.getLogradouro()).
				municipio(dto.getMunicipio()).
				numero(dto.getNumero()).
				razaoSocial(dto.getRazaoSocial()).
				telefone(dto.getTelefone()).build();
		try {
			Unidade unidadeSalva = service.novaUnidade(unidade);
			return new ResponseEntity(unidadeSalva,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
		
		
	}
	
}
