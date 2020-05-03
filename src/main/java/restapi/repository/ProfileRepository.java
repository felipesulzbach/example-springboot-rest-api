package restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.Profile;

/**
 * @autor: Felipe Sulzbach
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByName(String name);
}