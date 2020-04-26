package br.com.restapi.exemplorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restapi.exemplorestapi.models.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
}