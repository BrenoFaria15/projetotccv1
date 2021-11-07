package com.projeto.tccv1.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
				telefone(dto.getTelefone()).nomeFantasia(dto.getNomeFantasia()).build() ;
				
		try {
			Unidade unidadeSalva = service.novaUnidade(unidade);
			return new ResponseEntity(unidadeSalva,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
		
	}
	
	@GetMapping("/all")
	public @ResponseBody List<Unidade> buscarTodos(){
		return service.buscarTodos();
	}
	
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_unidade());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Unidade não encontrada", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id) {
		return service.buscarPorId(id).map(entity ->{
			service.deletar(entity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Unidade não encontrada", HttpStatus.BAD_REQUEST)); 
	}
	

	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody UnidadeDTO dto) {
		return service.buscarPorId(id).map(entity ->
		{
			try {
				Unidade unidade = new Unidade();
				
				unidade.setBairro(dto.getBairro());
				unidade.setCep(dto.getCep());
				unidade.setCnes(dto.getCnes());
				unidade.setComplemento(dto.getComplemento());
				unidade.setLogradouro(dto.getLogradouro());
				unidade.setMunicipio(dto.getMunicipio());
				unidade.setNumero(dto.getNumero());
				unidade.setRazaoSocial(dto.getRazaoSocial());
				unidade.setTelefone(dto.getTelefone());
				unidade.setNomeFantasia(dto.getNomeFantasia());
				unidade.setId_unidade(entity.getId_unidade());
				
				
				service.atualizar(unidade);
				return ResponseEntity.ok(unidade);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet(() -> new ResponseEntity("Unidade não encontrada", HttpStatus.BAD_REQUEST));
	}
	
}
