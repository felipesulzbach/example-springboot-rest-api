package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.SchoolClassResp;
import restapi.models.resources.transformer.SchoolClassRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.SchoolClassDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class SchoolClassRespDf extends DataFake<SchoolClassResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SchoolClassDf SchoolClassDf;

    @Override
    public SchoolClassResp getData() {
        return SchoolClassRespTrans.create().toTransform(SchoolClassDf.getData());
    }
}
