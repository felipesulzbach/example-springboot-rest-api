package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Teacher;

/**
 * @autor: Felipe Sulzbach
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}