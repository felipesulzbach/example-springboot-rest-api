package br.com.restapi.exemplorestapi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restapi.exemplorestapi.models.User;
import br.com.restapi.exemplorestapi.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
// @CrossOrigin(origins = "/http:dominio") // libera acesso a um domínio específico
@CrossOrigin(origins = "*") // libera o acesso para todos os domínios
@Api(value = "API REST com Swagger")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@ApiOperation(value = "Retorna uma lista de Usuarios")
	@GetMapping(value = "/usuario")
	public List<User> listarUsuario() {
		return usuarioRepository.findAll();
	}

	@ApiOperation(value = "Retorna um Usuario específico")
	@GetMapping(value = "/usuario/{id}")
	public User buscarUsuario(@PathVariable(value = "id") long id) {
		return usuarioRepository.findById(id).get();
	}

	@ApiOperation(value = "Insere um Usuario específico")
	@PostMapping(value = "/usuario")
	public User salvarUsuario(@RequestBody User usuario) {
		return usuarioRepository.save(usuario);
	}

	@ApiOperation(value = "Remove um Usuario específico")
	@DeleteMapping(value = "/usuario")
	public void removerUsuario(@RequestBody User usuario) {
		usuarioRepository.delete(usuario);
	}

	@ApiOperation(value = "Atualiza um Usuario específico")
	@PutMapping(value = "/usuario")
	public User atualizarUsuario(@RequestBody User usuario) {
		return usuarioRepository.save(usuario);
	}
}
