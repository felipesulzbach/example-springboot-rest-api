package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Person;

/**
 * @autor: Felipe Sulzbach
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByName(String name);
}