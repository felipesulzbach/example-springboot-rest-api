package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.SchoolClassReq;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SchoolClassReqDf extends DataFake<SchoolClassReq> {

    private static final long serialVersionUID = 1L;

    @Override
    public SchoolClassReq getData() {
        return SchoolClassReq.create().withCourseId(DataFakeUtil.getLong(3));
    }
}
