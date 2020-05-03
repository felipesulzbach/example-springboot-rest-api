package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Student;

/**
 * @autor: Felipe Sulzbach
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByName(String name);
}