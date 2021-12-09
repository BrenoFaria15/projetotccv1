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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.tccv1.api.dto.PacienteDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.service.PacienteService;
import com.projeto.tccv1.service.RelatorioPacienteService;

	@RestController
	@RequestMapping("/api/pacientes")
public class PacienteController {

	private PacienteService service;
	private RelatorioPacienteService relatorioService;
	
	@Autowired
	public void PacienteResource(PacienteService service,RelatorioPacienteService relatorioService) {
		this.service=service;
		this.relatorioService=relatorioService;
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
				dataNascimento(dto.getDataNascimento()).
				dataObito(dto.getDataObito()).
				flgAtivo(dto.isFlgAtivo()).
				flgObito(dto.isFlgObito()).
				logradouro(dto.getLogradouro()).
				municipio(dto.getMunicipio()).
				nome(dto.getNome()).
				nomeMae(dto.getNomeMae()).
				nomePai(dto.getNomePai()).
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
				paciente.setDataNascimento(dto.getDataNascimento());
				paciente.setDataObito(dto.getDataObito());
				paciente.setFlgAtivo(dto.isFlgAtivo());
				paciente.setFlgObito(dto.isFlgObito());
				paciente.setLogradouro(dto.getLogradouro());
				paciente.setMunicipio(dto.getMunicipio());
				paciente.setNome(dto.getNome());
				paciente.setNomeMae(dto.getNomeMae());
				paciente.setNomePai(dto.getNomePai());
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
	
	@GetMapping("/all")
	public @ResponseBody List<Paciente> buscarTodos(){
		return service.buscarTodos();
	}
	

	

@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_paciente());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Paciente não encontrado", HttpStatus.BAD_REQUEST));
	}

@GetMapping("/buscarporcns/")
public ResponseEntity  buscarporCns(@RequestParam("cns")String cns){
	return service.buscarPorCns(cns).map(entity ->{
		
		return ResponseEntity.ok(entity);
	}
	
			
			).orElseGet(() -> new ResponseEntity("Paciente não encontrado", HttpStatus.BAD_REQUEST));
}
	
	@GetMapping("/relatorio-paciente")
	public ResponseEntity<byte[]>relatorioPaciente(){
		byte[] relatorioGerado = relatorioService.gerarRelatorio();
		HttpHeaders headers = new HttpHeaders();
		String fileName = "listagem-paciente.pdf";
		
		headers.setContentDispositionFormData("inline;filename=\""+fileName+"\"",fileName);
		headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
		return  new ResponseEntity<>(relatorioGerado,headers,HttpStatus.OK);
		
		
	}
}
