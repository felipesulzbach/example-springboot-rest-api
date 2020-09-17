package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.enumeration.EnumAccessPermission;
import restapi.models.resources.ProfileReq;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ProfileReqDf extends DataFake<ProfileReq> {

    private static final long serialVersionUID = 1L;

    @Override
    public ProfileReq getData() {
        return ProfileReq.create().withName(DataFakeUtil.getString(8, false)).withCode(DataFakeUtil.getString(5, true))
                .withAccessPermission(EnumAccessPermission.getRandom());
    }
}
