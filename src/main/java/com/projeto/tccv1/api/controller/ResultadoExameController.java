package com.projeto.tccv1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.ResultadoExameDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Exame;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.ResultadoExame;
import com.projeto.tccv1.service.ExameService;
import com.projeto.tccv1.service.PacienteService;
import com.projeto.tccv1.service.ResultadoExameService;

@RestController
@RequestMapping("/api/resultadoexames")
public class ResultadoExameController {
	private ResultadoExameService service;
	private ExameService exameService;
	private PacienteService pacienteService;
	
	@Autowired
	public void ResultadoExameResource(ResultadoExameService service,ExameService exameService,PacienteService pacienteService) {
		this.service=service;
		this.exameService=exameService;
		this.pacienteService=pacienteService;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody ResultadoExameDTO dto) {
		try {
			ResultadoExame entidade = converter(dto);
			entidade = service.novoResultado(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody ResultadoExameDTO dto) {
		
		return service.buscarPorId(id).map(entity -> {
			try {
			ResultadoExame resultado = converter(dto);
			resultado.setId_resultado(entity.getId_resultado());
			service.atualizar(resultado);
			return ResponseEntity.ok(resultado);
		}catch(RegraNegocioException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		}).orElseGet(() -> new ResponseEntity("Resultado não encontrado", HttpStatus.BAD_REQUEST));
		
		
	}
	
	
	
	
	private ResultadoExame converter(ResultadoExameDTO dto) {
		ResultadoExame resultado = new ResultadoExame();
		
		resultado.setResultado(dto.getResultado());
		
		Exame exame = exameService.buscarPorId(dto.getId_exame())
				.orElseThrow(() -> new RegraNegocioException("Exame com esse id não encontrado"));
		resultado.setExame(exame);
		
		Paciente paciente = pacienteService.buscarPorId(dto.getId_paciente())
				.orElseThrow(() -> new RegraNegocioException("Paciente com esse id não encontrado"));
		resultado.setPaciente(paciente);
		return resultado;
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id ) {
		return service.buscarPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}).orElseGet(() ->  new ResponseEntity("Resultado não encontrado", HttpStatus.BAD_REQUEST)); 
	}
}
