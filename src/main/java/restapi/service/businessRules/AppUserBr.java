package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.AppUser;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.AppUserRepository;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AppUserBr extends BusinessRules<AppUser> {

    @Autowired
    private AppUserRepository repository;

    public void validateNameExists(String name) throws ServiceException {
        Optional<AppUser> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "User", "Name " + name);
        }
    }
}
