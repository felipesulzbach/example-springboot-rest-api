package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.AppUser;
import restapi.models.resources.AppUserResp;

/**
 * @author Felipe Sulzbach
 */
public class AppUserTrans implements Transformer<AppUserResp, AppUser> {

    private AppUserTrans() {
    }

    public static AppUserTrans create() {
        return new AppUserTrans();
    }

    @Override
    public AppUser toTransform(AppUserResp in) {
        if (in == null) {
            return null;
        }

        return AppUser.create().withId(in.getId()).withName(in.getName()).withPassword(in.getPassword())
                .withProfile(ProfileTrans.create().toTransform(in.getProfile()))
                .withPerson(PersonTrans.create().toTransform(in.getPerson())).withStartDate(in.getStartDate())
                .withEndDate(in.getEndDate()).withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<AppUser> toTransform(List<AppUserResp> inList) {
        if (inList == null) {
            return null;
        }

        List<AppUser> outList = new ArrayList<AppUser>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
