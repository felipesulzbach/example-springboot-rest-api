package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.AppUserResp;
import restapi.models.resources.transformer.AppUserRespTrans;
import restapi.util.dataFake.DataFake;
import restapi.util.dataFake.model.AppUserDf;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AppUserRespDf extends DataFake<AppUserResp> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppUserDf appUserDf;

    @Override
    public AppUserResp getData() {
        return AppUserRespTrans.create().toTransform(appUserDf.getData());
    }
}
