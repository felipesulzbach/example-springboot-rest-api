package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Course;

/**
 * @autor: Felipe Sulzbach
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}