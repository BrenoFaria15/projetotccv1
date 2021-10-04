package com.projeto.tccv1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.TipoAtendimentoDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.service.TipoAtendimentoService;


@RestController
@RequestMapping("/api/tipoatendimento")
public class TipoAtendimentoController {
	
	private TipoAtendimentoService service;
	
	@Autowired
	public void TipoAtendimentoResource(TipoAtendimentoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody TipoAtendimentoDTO dto) {
		
		TipoAtendimento tipoAtend = TipoAtendimento.builder().
				tipoNome(dto.getTipoNome()).
				Descricao(dto.getDescricao()).build();
				
		
		try {
			TipoAtendimento tipoAtendSalvo = service.novoTipoAtendimento(tipoAtend);
			return new ResponseEntity(tipoAtendSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
