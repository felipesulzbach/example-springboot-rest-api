package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.ProfileResp;
import restapi.models.resources.transformer.ProfileRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.ProfileDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProfileRespDf extends DataFake<ProfileResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProfileDf profileDf;

    @Override
    public ProfileResp getData() {
        return ProfileRespTrans.create().toTransform(profileDf.getData());
    }
}
