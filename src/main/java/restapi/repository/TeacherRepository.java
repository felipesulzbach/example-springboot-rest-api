package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Teacher;

/**
 * @autor: Felipe Sulzbach
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByName(String name);
}