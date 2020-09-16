package restapi.service.businessRules;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import restapi.models.resources.vo.ServiceException;

public abstract class BusinessRules<T> {

    public void validateEntityExists(Optional<T> entityOpt, Long id) throws ServiceException {
        if (entityOpt == null || !entityOpt.isPresent())
            throw ServiceException.get("ENTITY_NOT_FOUND", String.valueOf(id), getEntityName());
    }

    private String getEntityName() {
        String response = this.getClass().getSimpleName().indexOf("$$") > 0
                ? this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().indexOf("$$"))
                : this.getClass().getSimpleName();

        return response != null && !response.isEmpty() ? StringUtils.substring(response, 0, response.length() - 2) : "";
    }
}
