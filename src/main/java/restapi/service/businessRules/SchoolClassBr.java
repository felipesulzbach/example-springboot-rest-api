package restapi.service.businessRules;

import java.util.Optional;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import restapi.models.SchoolClass;
import restapi.models.resources.vo.ServiceException;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SchoolClassBr {

    public void validateEntityExists(Optional<SchoolClass> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), "Class");
    }
}
