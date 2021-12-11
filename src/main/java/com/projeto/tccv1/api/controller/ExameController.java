package com.projeto.tccv1.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.ExameDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Exame;
import com.projeto.tccv1.service.ExameService;
import com.projeto.tccv1.service.RelatorioExameService;



@RestController
@RequestMapping("/api/exames")
public class ExameController {
	private ExameService service;
	private RelatorioExameService relatorioService;
	
	@Autowired
	public void ExameResource(ExameService service, RelatorioExameService relatorioService) {
		this.service=service;
		this.relatorioService=relatorioService;
	}
	

@PostMapping
	public ResponseEntity salvar(@RequestBody ExameDTO dto) {
		Exame exame = Exame.builder()
				.codSus(dto.getCodSus())
				.descricao(dto.getDescricao())
				.nome(dto.getNome()).build();
		try {
			Exame exameSalvo = service.novoExame(exame);
			return new ResponseEntity(exameSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	
	
	}


@PutMapping("{id}")
public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody ExameDTO dto) {
	
	return service.buscarPorId(id).map(entity -> {
		
		try {

			Exame exame = new Exame();
			
			exame.setId_exame(dto.getId_exame());
			exame.setDescricao(dto.getDescricao());
			exame.setNome(dto.getNome());
			exame.setCodSus(dto.getCodSus());
			
			exame.setId_exame(entity.getId_exame());
			service.atualizar(exame);
			return ResponseEntity.ok(exame);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	
	}).orElseGet(() -> new ResponseEntity("Exame não encontrado", HttpStatus.BAD_REQUEST));
	

	
	
}

@DeleteMapping("{id}")
public ResponseEntity deletar(@PathVariable("id")Long id) {
	return service.buscarPorId(id).map(entidade ->{
		service.deletar(entidade);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}).orElseGet(() ->  new ResponseEntity("Exame não encontrado", HttpStatus.BAD_REQUEST)); 
}


@GetMapping("/all")
public @ResponseBody List<Exame> buscarTodos(){
	return service.buscarTodos();
}


@GetMapping("/buscarporid/{id}")
public ResponseEntity  buscarporId(@PathVariable("id")Long id){
	return service.buscarPorId(id).map(entity ->{
		service.buscarPorId(entity.getId_exame());
		return ResponseEntity.ok(entity);
	}
	
			
			).orElseGet(() -> new ResponseEntity("Exame não encontrado", HttpStatus.BAD_REQUEST));
}

@GetMapping("/relatorio-exame")
	public ResponseEntity<byte[]>relatorioUnidade(){
		byte[] relatorioGerado = relatorioService.gerarRelatorio();
		HttpHeaders headers = new HttpHeaders();
		String fileName = "listagem-exame.pdf";
		
		headers.setContentDispositionFormData("inline;filename=\""+fileName+"\"",fileName);
		headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
		return  new ResponseEntity<>(relatorioGerado,headers,HttpStatus.OK);
		
		
	}
	
}
