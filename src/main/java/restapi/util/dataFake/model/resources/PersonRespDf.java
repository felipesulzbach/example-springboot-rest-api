package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.PersonResp;
import restapi.models.resources.transformer.PersonRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.PersonDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class PersonRespDf extends DataFake<PersonResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonDf PersonDf;

    @Override
    public PersonResp getData() {
        return PersonRespTrans.create().toTransform(PersonDf.getData());
    }
}
