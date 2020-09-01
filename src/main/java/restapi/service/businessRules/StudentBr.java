package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import restapi.models.Student;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.StudentRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class StudentBr {

    @Autowired
    private StudentRepository repository;

    public void validateEntityExists(Optional<Student> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Student");
    }

    public void validateNameExists(String name) throws ServiceException {
        Optional<Student> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Student", "Name " + name);
        }
    }
}
