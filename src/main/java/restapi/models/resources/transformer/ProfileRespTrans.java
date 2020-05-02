package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Profile;
import restapi.models.resources.ProfileResp;

/**
 * @author felipe.sulzbach
 */
public class ProfileRespTrans implements Transformer<Profile, ProfileResp> {

    private ProfileRespTrans() {
    }

    public static ProfileRespTrans create() {
        return new ProfileRespTrans();
    }

    @Override
    public ProfileResp toTransform(Profile in) {
        if (in == null) {
            return null;
        }

        return ProfileResp.create().withId(in.getId()).withName(in.getName()).withCode(in.getCode())
                .withAccessPermission(in.getAccessPermission());
    }

    @Override
    public List<ProfileResp> toTransform(List<Profile> inList) {
        if (inList == null) {
            return null;
        }

        List<ProfileResp> outList = new ArrayList<ProfileResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
