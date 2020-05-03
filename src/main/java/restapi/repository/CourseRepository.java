package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Course;

/**
 * @autor: Felipe Sulzbach
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String name);
}