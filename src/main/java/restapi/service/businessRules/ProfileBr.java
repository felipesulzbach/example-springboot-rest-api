package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import restapi.models.Profile;
import restapi.repository.ProfileRepository;
import restapi.util.ServiceException;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProfileBr {

    @Autowired
    private ProfileRepository repository;

    public void validateEntityExists(Optional<Profile> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Profile");
    }

    public void validateNameExists(String name) throws ServiceException {
        Optional<Profile> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Profile", "Name " + name);
        }
    }
}
