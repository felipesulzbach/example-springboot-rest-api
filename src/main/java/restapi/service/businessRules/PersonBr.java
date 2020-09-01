package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import restapi.models.Person;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.PersonRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PersonBr {

    @Autowired
    private PersonRepository repository;

    public void validateEntityExists(Optional<Person> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Person");
    }

    public void validateNameExists(String name) throws ServiceException {
        Optional<Person> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Person", "Name " + name);
        }
    }
}
