package restapi.util.dataFake.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Profile;
import restapi.models.enumeration.EnumAccessPermission;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

@Component
@Scope("singleton")
public class ProfileDf extends DataFake<Profile> {

    private static final long serialVersionUID = 1L;

    public Profile getData() {
        return Profile.create().withId(DataFakeUtil.getLong(3)).withName(DataFakeUtil.getString(8, false))
                .withCode(DataFakeUtil.getString(5, true)).withAccessPermission(EnumAccessPermission.getRandom());
    }
}