package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Profile;
import restapi.models.resources.ProfileResp;

/**
 * @author Felipe Sulzbach
 */
public class ProfileTrans implements Transformer<ProfileResp, Profile> {

    private ProfileTrans() {
    }

    public static ProfileTrans create() {
        return new ProfileTrans();
    }

    @Override
    public Profile toTransform(ProfileResp in) {
        if (in == null) {
            return null;
        }

        return Profile.create().withId(in.getId()).withName(in.getName()).withCode(in.getCode())
                .withAccessPermission(in.getAccessPermission());
    }

    @Override
    public List<Profile> toTransform(List<ProfileResp> inList) {
        if (inList == null) {
            return null;
        }

        List<Profile> outList = new ArrayList<Profile>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
