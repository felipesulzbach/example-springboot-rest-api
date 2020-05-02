package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.AppUser;
import restapi.models.resources.AppUserResp;

/**
 * @author felipe.sulzbach
 */
public class AppUserRespTrans implements Transformer<AppUser, AppUserResp> {

    private AppUserRespTrans() {
    }

    public static AppUserRespTrans create() {
        return new AppUserRespTrans();
    }

    @Override
    public AppUserResp toTransform(AppUser in) {
        if (in == null) {
            return null;
        }

        return AppUserResp.create().withId(in.getId()).withName(in.getName())
                .withProfile(ProfileRespTrans.create().toTransform(in.getProfile()))
                .withPerson(PersonRespTrans.create().toTransform(in.getPerson())).withStartDate(in.getStartDate())
                .withEndDate(in.getEndDate()).withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<AppUserResp> toTransform(List<AppUser> inList) {
        if (inList == null) {
            return null;
        }

        List<AppUserResp> outList = new ArrayList<AppUserResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
