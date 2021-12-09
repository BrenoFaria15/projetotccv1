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

import com.projeto.tccv1.api.dto.UsuarioDTO;
import com.projeto.tccv1.exeception.ErroAutenticacao;
import com.projeto.tccv1.exeception.RegraNegocioException;
import com.projeto.tccv1.model.entity.Usuario;
import com.projeto.tccv1.service.RelatorioUsuarioService;
import com.projeto.tccv1.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private UsuarioService service;
	private RelatorioUsuarioService relatorioService;
	
	@Autowired
	public void UsuarioResource(UsuarioService service,RelatorioUsuarioService relatorioService) {
		this.service = service;
		this.relatorioService= relatorioService;
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
		Usuario	usuarioAutenticado= service.autenticar(dto.getNome(), dto.getSenha());
		return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder().
				nome(dto.getNome()).
				senha(dto.getSenha()).
				tipoUsuario(dto.getTipoUsuario()).build();
		
		try {
			Usuario usuarioSalvo = service.novoUsuario(usuario);
			return new ResponseEntity(usuarioSalvo,HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		
	}
	
	@GetMapping("/all")
	public @ResponseBody List<Usuario> buscarTodos(){
		return service.buscarTodos();
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id")Long id,@RequestBody UsuarioDTO dto) {
		return service.buscarPorId(id).map(entity ->
		{
			try {
				Usuario usuario = new Usuario();
				
				usuario.setNome(dto.getNome());
				usuario.setSenha(dto.getSenha());
				usuario.setTipoUsuario(dto.getTipoUsuario());
				usuario.setId_usuario(entity.getId_usuario());
				service.atualizar(usuario);
				return ResponseEntity.ok(usuario);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet(() -> new ResponseEntity("Usuario não encontrado", HttpStatus.BAD_REQUEST));
	}
	
	@GetMapping("/buscarporid/{id}")
	public ResponseEntity  buscarporId(@PathVariable("id")Long id){
		return service.buscarPorId(id).map(entity ->{
			service.buscarPorId(entity.getId_usuario());
			return ResponseEntity.ok(entity);
		}
		
				
				).orElseGet(() -> new ResponseEntity("Usuario não encontrado", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id")Long id) {
		return service.buscarPorId(id).map(entity ->{
			service.deletar(entity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() ->  new ResponseEntity("Usuario não encontrado", HttpStatus.BAD_REQUEST)); 
	}
	
	@GetMapping("/relatorio-usuario")
	public ResponseEntity<byte[]>relatorioUsuario(){
		byte[] relatorioGerado = relatorioService.gerarRelatorio();
		HttpHeaders headers = new HttpHeaders();
		String fileName = "listagem-usuario.pdf";
		
		headers.setContentDispositionFormData("inline;filename=\""+fileName+"\"",fileName);
		headers.setCacheControl("must-revalidate,post-check=0,pre-check=0");
		return  new ResponseEntity<>(relatorioGerado,headers,HttpStatus.OK);
		
		
	}
	
	
	
}
