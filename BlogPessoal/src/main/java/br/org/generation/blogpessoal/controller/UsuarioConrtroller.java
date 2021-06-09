package br.org.generation.blogpessoal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.UserLogin;
import br.org.generation.blogpessoal.model.Usuario;
import br.org.generation.blogpessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class UsuarioConrtroller {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
		@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autetication(@RequestBody Optional<UserLogin> user){
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<Usuario>> Post(@RequestBody Usuario usuario){
		
		Optional<Usuario> user = usuarioService.CadastrarUsuario(usuario);
		
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
			
		}catch (Exception e){
			
			return ResponseEntity.badRequest().build();
			
		}
		/*return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));*/
	}


}
