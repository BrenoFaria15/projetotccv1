package com.projeto.tccv1.api.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

import com.projeto.tccv1.api.dto.AtendimentoDTO;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Agenda;
import com.projeto.tccv1.model.entity.Atendimento;
import com.projeto.tccv1.model.entity.Exame;
import com.projeto.tccv1.model.entity.Paciente;
import com.projeto.tccv1.model.entity.Profissional;
import com.projeto.tccv1.model.entity.TipoAtendimento;
import com.projeto.tccv1.model.entity.Unidade;
import com.projeto.tccv1.model.entity.Usuario;
import com.projeto.tccv1.service.AgendaService;
import com.projeto.tccv1.service.AtendimentoService;
import com.projeto.tccv1.service.PacienteService;
import com.projeto.tccv1.service.ProfissionalService;
import com.projeto.tccv1.service.RelatorioAtendimentoService;
import com.projeto.tccv1.service.TipoAtendimentoService;
import com.projeto.tccv1.service.UnidadeService;
import com.projeto.tccv1.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/atendimentos")
@RequiredArgsConstructor
public class AtendimentoContoller {
	
	private final AtendimentoService service;
	private final AgendaService agendaService;
	private  final PacienteService pacienteService;
	private final  ProfissionalService profissionalService;
	private  final TipoAtendimentoService tipoAtendService;
	private  final UnidadeService unidadeService;
	private final UsuarioService usuarioService;
	private final RelatorioAtendimentoService relatorioService;
	
	
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AtendimentoDTO dto) {
		try {
			Atendimento entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id,@RequestBody AtendimentoDTO dto) {
		
		return service.buscarPorId(id).map(entity -> {
			try {
			Atendimento atendimento = converter(dto);
			atendimento.setId_atendimento(entity.getId_atendimento());
			service.atualizar(atendimento);
			return ResponseEntity.ok(atendimento);
		}catch(RegraNegocioException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		}).orElseGet(() -> new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST));
		
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id ) {
		return service.buscarPorId(id).map(entidade -> {
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}).orElseGet(() ->  new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST)); 
	}
	
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value="data")String data,						
			 @RequestParam(value="idPaciente",required = false)Long idPaciente,
			 @RequestParam(value="idProfissional",required = false)Long idProfissional
								) {
		Atendimento atendimentoFiltro = new Atendimento();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
		LocalDate dataConvertida;
		
		
			dataConvertida =  LocalDate.parse(data);
			atendimentoFiltro.setData(dataConvertida);
		
        if(idPaciente!= 0){
		Optional<Paciente>paciente = pacienteService.buscarPorId(idPaciente);
		if(!paciente.isPresent()) {
			return ResponseEntity.badRequest().body("Paciente com esse id não encontrado");
		}else {
			atendimentoFiltro.setPaciente(paciente.get());
		}
        }
		
        if(idProfissional!= 0){
		Optional<Profissional>profissional = profissionalService.buscarPorId(idProfissional);
		if(!profissional.isPresent()) {
			return ResponseEntity.badRequest().body("Profissional com esse id não encontrado");
		}else {
			atendimentoFiltro.setProfissional(profissional.get());
		}
        }
		
		List<Atendimento> atendimentos= service.buscar(atendimentoFiltro);
		return ResponseEntity.ok(atendimentos);
	}
	
	/*@GetMapping("/buscarporpaciente/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPaciente(entity.getPaciente().getId_paciente());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Paciente não encontrado", HttpStatus.BAD_REQUEST));
	}*/
	
	@GetMapping("/buscarporpaciente/{id}")
	public @ResponseBody List<Atendimento> buscarPaciente(@PathVariable("id")String id){
		long idInt = Integer.parseInt(id);
		Paciente pacienteFiltro = pacienteService.buscarPorId(idInt).get();
		return service.buscarPaciente(pacienteFiltro);
	}
	
	@GetMapping("/buscaratendimentos")
	public @ResponseBody List<Atendimento> buscarAtendimentos(
			@RequestParam(value="data")String data,						
			 @RequestParam(value="idPaciente",required = false)Long idPaciente,
			@RequestParam(value="idProfissional",required = false)Long idProfissional,
			@RequestParam(value="idUnidade",required = false)Long idUnidade) throws ParseException{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
		LocalDate dataConvertida;
		
		
	
			dataConvertida = LocalDate.parse(data);
			
			
		
		
		Unidade unidadeFiltro = unidadeService.buscarPorId(idUnidade).get();
		if(idPaciente != null && idProfissional==null) {

			Paciente pacienteFiltro = pacienteService.buscarPorId(idPaciente).get();
			
			return service.buscarAtendimentoPaciente(pacienteFiltro, dataConvertida, unidadeFiltro);
		}else if(idPaciente==null &&  idProfissional !=null){
			Profissional profissionalFiltro =profissionalService.buscarPorId(idProfissional).get();
			return service.buscarAtendimentoProfissional(profissionalFiltro, dataConvertida, unidadeFiltro);
		}else if(idPaciente==null && idProfissional==null) {
			return service.buscarAtendimentoData(dataConvertida, unidadeFiltro);
		}else {
			Paciente pacienteFiltro = pacienteService.buscarPorId(idPaciente).get();
			Profissional profissionalFiltro =profissionalService.buscarPorId(idProfissional).get();
			return service.buscarAtendimento(pacienteFiltro, profissionalFiltro,dataConvertida,unidadeFiltro);
		}
		
	}
	
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_atendimento());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Atendimento não encontrado", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping("/relatorio-atendimento")
	public ResponseEntity<byte[]>relatorioAtendimento(@RequestParam(value = "dataInicio", required = false, defaultValue = "") String inicio,
			@RequestParam(value = "dataFim", required= false, defaultValue = "") String fim,
			@RequestParam(value = "idPaciente", required= false, defaultValue = "") Long idPaciente,
			@RequestParam(value = "idProfissional", required= false, defaultValue = "") Long idProfissional) throws ParseException{
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date dataInicio,dataFim;
		
		dataInicio = formato.parse(inicio);
		dataFim = formato.parse(fim);
		
		byte[] relatorioGerado = relatorioService.gerarRelatorio(idPaciente,idProfissional,dataInicio,dataFim);
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = "listagem-atendimentos.pdf";
		
		headers.setContentDispositionFormData("inline;filename=\""+fileName+"\"",fileName);
		headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
		return  new ResponseEntity<>(relatorioGerado,headers,HttpStatus.OK);
		
		
	}
	
	
	

	private Atendimento converter(AtendimentoDTO dto) {
		Atendimento atendimento = new Atendimento();
		
		LocalDate dataConvertida;
		
		
		
		
		
		atendimento.setId_atendimento(dto.getId_atendimento());
		atendimento.setAltura(dto.getAltura());
		atendimento.setAvaliacao(dto.getAvaliacao());
		atendimento.setBpm(dto.getBpm());
		atendimento.setCid(dto.getCid());
		atendimento.setData(dto.getData());
		atendimento.setEntrevistaClinica(dto.getEntrevistaClinica());
		atendimento.setFlg_atendido(dto.isFlg_atendido());
		atendimento.setGlicemia(dto.getGlicemia());
		atendimento.setHoraFim(dto.getHoraFim());
		atendimento.setHoraInicio(dto.getHoraInicio());
		atendimento.setImc(dto.getImc());
		atendimento.setPeso(dto.getPeso());
		atendimento.setSaturacao(dto.getSaturacao());
		atendimento.setTemperatura(dto.getTemperatura());
		atendimento.setPressao1(dto.getPressao1());
		atendimento.setPressao2(dto.getPressao2());
		atendimento.setHipotese(dto.getHipotese());
		
		//Agenda agenda =  agendaService.buscarPorId(dto.getId_agenda())
		//.orElseThrow(() -> new RegraNegocioException("Agenda com esse id não encontrado"));
		//atendimento.setAgenda(agenda);
		
		Agenda agenda =  agendaService.buscarPorId(dto.getId_agenda()).orElse(null);
		atendimento.setAgenda(agenda);
				
		Paciente paciente =pacienteService.buscarPorId(dto.getPaciente())
				.orElseThrow(() -> new RegraNegocioException("Paciente com esse id não encontrado"));
		atendimento.setPaciente(paciente);
		
		Profissional profissional = profissionalService.buscarPorId(dto.getProfissional())
				.orElseThrow(() -> new RegraNegocioException("Profissional com esse id não encontrado"));
		atendimento.setProfissional(profissional);
		
		TipoAtendimento tipoAtendimento = tipoAtendService.buscarPorId(dto.getTipoatendimento())
				.orElseThrow(() -> new RegraNegocioException("Tipo de Atendimento com esse id não encontrado"));
		atendimento.setTipoatendimento(tipoAtendimento);
		
		Unidade unidade = unidadeService.buscarPorId(dto.getUnidade())
				.orElseThrow(() -> new RegraNegocioException("Unidade de Atendimento com esse id não encontrada"));
		atendimento.setUnidade(unidade);
		
		Usuario usuario = usuarioService.buscarPorId(dto.getUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuario com esse id não encontrado"));
		atendimento.setUsuario(usuario);
		
		
		
		
		
		
		
		
		return atendimento;
	}

}
