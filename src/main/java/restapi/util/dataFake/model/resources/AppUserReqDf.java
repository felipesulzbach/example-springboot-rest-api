package restapi.util.dataFake.model.resources;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.AppUserReq;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AppUserReqDf extends DataFake<AppUserReq> {

    private static final long serialVersionUID = 1L;

    @Override
    public AppUserReq getData() {
        return AppUserReq.create().withName(DataFakeUtil.getString(8, false))
                .withPassword(DataFakeUtil.getString(8, true)).withPersonId(DataFakeUtil.getLong(3))
                .withProfileId(DataFakeUtil.getLong(3)).withExpirationDate(DataFakeUtil
                        .getLocalDateTime(LocalDateTime.now().plusDays(1L), LocalDateTime.now().plusDays(8L)));
    }
}
