package restapi.util.dataFake.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.AppUser;
import restapi.models.resources.vo.ServiceException;
import restapi.util.CriptoUtil;
import restapi.util.CriptoUtil.EnumHash;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

@Component
@Scope("singleton")
public class AppUserDf extends DataFake<AppUser> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private PersonDf personDf;

    @Autowired
    private ProfileDf profileDf;

    public AppUser getData() {
        try {
            return AppUser.create().withId(DataFakeUtil.getLong(3)).withName(DataFakeUtil.getString(8, false))
                    .withPassword(CriptoUtil.encript(DataFakeUtil.getString(8, true), EnumHash.SHA_256))
                    .withPerson(this.personDf.getData()).withProfile(this.profileDf.getData())
                    .withStartDate(LocalDateTime.now())
                    .withEndDate(DataFakeUtil.getLocalDateTime(LocalDateTime.now().plusDays(1L),
                            LocalDateTime.now().plusDays(8L)))
                    .withRegistrationDate(
                            DataFakeUtil.getLocalDateTime(LocalDateTime.now(), LocalDateTime.now().plusDays(5L)));
        } catch (ServiceException e) {
            return AppUser.create();
        }
    }
}