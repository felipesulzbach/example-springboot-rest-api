package restapi.service.businessRules;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.SchoolClass;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SchoolClassBr extends BusinessRules<SchoolClass> {
}
