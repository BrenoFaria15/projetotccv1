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

import com.projeto.tccv1.api.dto.ProfissionalDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.service.ProfissionalService;
import com.projeto.tccv1.service.RelatorioProfissionalService;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {
	
	private ProfissionalService service;
	private RelatorioProfissionalService relatorioService;
	
	@Autowired
	public void ProfissionalResource(ProfissionalService service,RelatorioProfissionalService relatorioService) {
		this.service=service;
		this.relatorioService=relatorioService;
	}
	
	@PostMapping
	public ResponseEntity salvarProfissional(@RequestBody ProfissionalDTO dto) {
		
		Profissional profissional = Profissional.builder().
				bairro(dto.getBairro()).
				cdConselho(dto.getCdConselho()).
				celular(dto.getCelular()).
				cep(dto.getCep()).
				cns(dto.getCns()).
				complemento(dto.getComplemento()).
				cpf(dto.getCpf()).
				dataNascimento(dto.getDataNascimento()).
				especialidade(dto.getEspecialidade()).
				flgAtivo(dto.isFlgAtivo()).
				logradouro(dto.getLogradouro()).
				municipio(dto.getMunicipio()).
				nome(dto.getNome()).
				nomeConselho(dto.getNomeConselho()).
				numero(dto.getNumero()).
				rg(dto.getRg()).build();		
		try {
			Profissional profissionalSalvo = service.novoProfissional(profissional);
			return new ResponseEntity(profissionalSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody ProfissionalDTO dto) {
		
		return service.buscarPorId(id).map(entity -> {
			
			try {

				Profissional profissional = new Profissional();
				
				
				profissional.setBairro(dto.getBairro());
				profissional.setCdConselho(dto.getCdConselho());
				profissional.setCelular(dto.getCelular());
				profissional.setCep(dto.getCep());
				profissional.setCns(dto.getCns());
				profissional.setComplemento(dto.getComplemento());
				profissional.setCpf(dto.getCpf());
				profissional.setDataNascimento(dto.getDataNascimento());
				profissional.setEspecialidade(dto.getEspecialidade());
				profissional.setFlgAtivo(dto.isFlgAtivo());
				profissional.setLogradouro(dto.getLogradouro());
				profissional.setMunicipio(dto.getMunicipio());
				profissional.setNome(dto.getNome());
				profissional.setNomeConselho(dto.getNomeConselho());
				profissional.setNumero(dto.getNumero());
				profissional.setRg(dto.getRg());
				profissional.setId_profissional(entity.getId_profissional());
				
				
				service.atualizar(profissional);
				return ResponseEntity.ok(profissional);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		
		}).orElseGet(() -> new ResponseEntity("Profissional não encontrado", HttpStatus.BAD_REQUEST));
		
	
		
		
	}
	
	@GetMapping("/all")
	public @ResponseBody List<Profissional> buscarTodos(){
		return service.buscarTodos();
	}
	
	@GetMapping("/allfiltro")
	public @ResponseBody List<Profissional> buscarTodosFiltro(){
		return service.buscarTodosFiltro();
	}
	
	
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id) {
		return service.buscarPorId(id).map(entity ->{
			service.deletar(entity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Profissional não encontrado", HttpStatus.BAD_REQUEST)); 
	}
	
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_profissional());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Profissional não encontrado", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping("/relatorio-profissional")
	public ResponseEntity<byte[]>relatorioProfissional(){
		byte[] relatorioGerado = relatorioService.gerarRelatorio();
		HttpHeaders headers = new HttpHeaders();
		String fileName = "listagem-profissional.pdf";
		
		headers.setContentDispositionFormData("inline;filename=\""+fileName+"\"",fileName);
		headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
		return  new ResponseEntity<>(relatorioGerado,headers,HttpStatus.OK);
		
		
	}



}
