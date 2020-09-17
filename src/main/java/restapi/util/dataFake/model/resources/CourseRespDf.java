package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.CourseResp;
import restapi.models.resources.transformer.CourseRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.CourseDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CourseRespDf extends DataFake<CourseResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CourseDf CourseDf;

    @Override
    public CourseResp getData() {
        return CourseRespTrans.create().toTransform(CourseDf.getData());
    }
}
