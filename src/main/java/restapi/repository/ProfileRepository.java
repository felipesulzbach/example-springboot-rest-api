package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Profile;

/**
 * @autor: Felipe Sulzbach
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}