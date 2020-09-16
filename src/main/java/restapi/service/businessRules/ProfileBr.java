package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Profile;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.ProfileRepository;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProfileBr extends BusinessRules<Profile> {

    @Autowired
    private ProfileRepository repository;

    public void validateNameExists(String name) throws ServiceException {
        Optional<Profile> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Profile", "Name " + name);
        }
    }
}
