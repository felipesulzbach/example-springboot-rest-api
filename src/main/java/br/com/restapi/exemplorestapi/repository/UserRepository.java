package br.com.restapi.exemplorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.restapi.exemplorestapi.models.User;

/**
 * @autor: Felipe Sulzbach
 */
public interface UserRepository extends JpaRepository<User, Long> {
}