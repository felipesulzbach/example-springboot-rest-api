package restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restapi.models.AppUser;

/**
 * @autor: Felipe Sulzbach
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}