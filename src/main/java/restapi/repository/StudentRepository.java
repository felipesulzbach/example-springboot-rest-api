package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Student;

/**
 * @autor: Felipe Sulzbach
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}