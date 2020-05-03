package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.SchoolClass;

/**
 * @autor: Felipe Sulzbach
 */
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}