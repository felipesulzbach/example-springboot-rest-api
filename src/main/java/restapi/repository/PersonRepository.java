package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Person;

/**
 * @autor: Felipe Sulzbach
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}