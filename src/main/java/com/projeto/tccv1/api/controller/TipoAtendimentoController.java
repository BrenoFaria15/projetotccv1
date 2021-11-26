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

import com.projeto.tccv1.api.dto.TipoAtendimentoDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.service.TipoAtendimentoService;


@RestController
@RequestMapping("/api/tipoatendimentos")
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
	
	@GetMapping("/all")
	public @ResponseBody List<TipoAtendimento> buscarTodos(){
		return service.buscarTodos();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id) {
		return service.buscarPorId(id).map(entidade ->{
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Tipo Atendimento não encontrado", HttpStatus.BAD_REQUEST)); 
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody TipoAtendimentoDTO dto) {
		
		return service.buscarPorId(id).map(entity -> {
			
			try {

				TipoAtendimento TipoAtend = new TipoAtendimento();

				
				TipoAtend.setId_tipo_atendimento(dto.getId_tipo_atendimento());
				TipoAtend.setTipoNome(dto.getTipoNome());
				TipoAtend.setDescricao(dto.getDescricao());
				
				
				TipoAtend.setId_tipo_atendimento(entity.getId_tipo_atendimento());
				service.atualizar(TipoAtend);
				return ResponseEntity.ok(TipoAtend);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		
		}).orElseGet(() -> new ResponseEntity("Exame não encontrado", HttpStatus.BAD_REQUEST));
		

		
		
	}
	
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_tipo_atendimento());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Tipo Atendimento não encontrado", HttpStatus.BAD_REQUEST));
	}
		
	

	

}
