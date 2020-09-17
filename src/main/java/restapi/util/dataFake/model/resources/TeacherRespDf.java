package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.TeacherResp;
import restapi.models.resources.transformer.TeacherRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.TeacherDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class TeacherRespDf extends DataFake<TeacherResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TeacherDf TeacherDf;

    @Override
    public TeacherResp getData() {
        return TeacherRespTrans.create().toTransform(TeacherDf.getData());
    }
}
