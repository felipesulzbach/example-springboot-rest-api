package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Teacher;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.TeacherRepository;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TeacherBr extends BusinessRules<Teacher> {

    @Autowired
    private TeacherRepository repository;

    public void validateNameExists(String name) throws ServiceException {
        Optional<Teacher> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Teacher", "Name " + name);
        }
    }
}
