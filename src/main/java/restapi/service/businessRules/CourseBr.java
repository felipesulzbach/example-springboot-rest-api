package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Course;
import restapi.models.resources.vo.ServiceException;
import restapi.repository.CourseRepository;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CourseBr extends BusinessRules<Course> {

    @Autowired
    private CourseRepository repository;

    public void validateNameExists(String name) throws ServiceException {
        Optional<Course> entityOpt = repository.findByName(name);
        if (entityOpt != null && entityOpt.isPresent()) {
            throw ServiceException.get("ENTITY_ALREADY_EXISTS", "Course", "Name " + name);
        }
    }
}
