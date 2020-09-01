package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import restapi.models.Teacher;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.TeacherRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TeacherBr {

    @Autowired
    private TeacherRepository repository;

    public void validateEntityExists(Optional<Teacher> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Teacher");
    }

    public void validateNameExists(String name) throws ServiceException {
        Optional<Teacher> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Teacher", "Name " + name);
        }
    }
}
