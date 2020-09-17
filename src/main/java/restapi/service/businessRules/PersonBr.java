package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Person;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.PersonRepository;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PersonBr extends BusinessRules<Person> {

    @Autowired
    private PersonRepository repository;

    public void validateNameExists(String name) throws ServiceException {
        Optional<Person> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Person", "Name " + name);
        }
    }
}
