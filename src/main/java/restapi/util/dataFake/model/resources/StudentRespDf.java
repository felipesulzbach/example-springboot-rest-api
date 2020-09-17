package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.StudentResp;
import restapi.models.resources.transformer.StudentRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.StudentDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class StudentRespDf extends DataFake<StudentResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StudentDf StudentDf;

    @Override
    public StudentResp getData() {
        return StudentRespTrans.create().toTransform(StudentDf.getData());
    }
}
