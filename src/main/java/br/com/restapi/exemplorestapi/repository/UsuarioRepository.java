package br.com.restapi.exemplorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restapi.exemplorestapi.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}