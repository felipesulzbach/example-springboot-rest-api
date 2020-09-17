package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.CourseReq;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CourseReqDf extends DataFake<CourseReq> {

    private static final long serialVersionUID = 1L;

    @Override
    public CourseReq getData() {
        return CourseReq.create().withName(DataFakeUtil.getString(8, false))
                .withDescription(DataFakeUtil.getString(12, false));
    }
}
