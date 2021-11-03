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
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody PacienteDTO dto) {
		
		return service.buscarPorId(id).map(entity -> {
			
			try {

				Paciente paciente = new Paciente();
				
				
				paciente.setId_paciente(dto.getId_paciente());
				paciente.setBairro(dto.getBairro());
				paciente.setCelular(dto.getCelular());
				paciente.setCep(dto.getCep());
				paciente.setCns(dto.getCns());
				paciente.setComplemento(dto.getComplemento());
				paciente.setConvenio(dto.getConvenio());
				paciente.setCpf(dto.getCpf());
				paciente.setData_nascimento(dto.getData_nascimento());
				paciente.setData_obito(dto.getData_obito());
				paciente.setFlg_ativo(dto.isFlg_ativo());
				paciente.setFlg_obito(dto.isFlg_obito());
				paciente.setLogradouro(dto.getLogradouro());
				paciente.setMunicipio(dto.getMunicipio());
				paciente.setNome(dto.getNome());
				paciente.setNome_mae(dto.getNome_mae());
				paciente.setNome_pai(dto.getNome_pai());
				paciente.setNumero(dto.getNumero());
				paciente.setRg(dto.getRg());
				
				paciente.setId_paciente(entity.getId_paciente());
				service.atualizar(paciente);
				return ResponseEntity.ok(paciente);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		
		}).orElseGet(() -> new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST));
		
	
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id) {
		return service.buscarPorId(id).map(entidade ->{
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST)); 
	}
	
	
	
}
