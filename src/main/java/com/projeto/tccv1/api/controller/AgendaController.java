package com.projeto.tccv1.api.controller;

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

import com.projeto.tccv1.api.dto.AgendaDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.model.entity.Usuario;
import com.projeto.tccv1.service.AgendaService;
import com.projeto.tccv1.service.AtendimentoService;
import com.projeto.tccv1.service.PacienteService;
import com.projeto.tccv1.service.ProfissionalService;
import com.projeto.tccv1.service.UnidadeService;
import com.projeto.tccv1.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
public class AgendaController {
	
	private final AgendaService service;
	private  final PacienteService pacienteService;
	private final  ProfissionalService profissionalService;
	private  final UnidadeService unidadeService;
	private final UsuarioService usuarioService;
	private final AtendimentoService atendimentoService;
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AgendaDTO dto) {
		try {
			Agenda entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody AgendaDTO dto) {
		return service.buscarPorId(id).map( entity ->{
			try {
				Agenda agenda = converter(dto);
				agenda.setId_agenda(entity.getId_agenda());
				service.atualizar(agenda);
				return ResponseEntity.ok(agenda);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet(() -> new ResponseEntity("Agendamento não encontrado",HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id ) {
		return service.buscarPorId(id).map(entidade ->{
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST)); 	
		}
	
	private Agenda converter (AgendaDTO dto) {
		Agenda agenda = new Agenda();
		
		agenda.setData(dto.getData());
		agenda.setFlg_atendido(dto.isFlg_atendido());
		agenda.setHora(dto.getHora());
		
		Paciente paciente =pacienteService.buscarPorId(dto.getPaciente())
				.orElseThrow(() -> new RegraNegocioException("Paciente com esse id não encontrado"));
		agenda.setPaciente(paciente);
		
		Profissional profissional = profissionalService.buscarPorId(dto.getProfissional())
				.orElseThrow(() -> new RegraNegocioException("Profissional com esse id não encontrado"));
		agenda.setProfissional(profissional);
		
		Unidade unidade = unidadeService.buscarPorId(dto.getUnidade())
				.orElseThrow(() -> new RegraNegocioException("Unidade de Atendimento com esse id não encontrada"));
		agenda.setUnidade(unidade);
		
		Usuario usuario = usuarioService.buscarPorId(dto.getUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuario com esse id não encontrado"));
		agenda.setUsuario(usuario);
		
		Atendimento atendimento = atendimentoService.buscarPorId(dto.getAtendimento())
				.orElseThrow(() -> new RegraNegocioException("Atendimento com esse id não encontrado"));
		agenda.setAtendimento(atendimento);
		
		return agenda;
	}
}
