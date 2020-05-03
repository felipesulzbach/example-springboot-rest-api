package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.SchoolClass;
import restapi.models.resources.SchoolClassResp;

/**
 * @author felipe.sulzbach
 */
public class SchoolClassRespTrans implements Transformer<SchoolClass, SchoolClassResp> {

    private SchoolClassRespTrans() {
    }

    public static SchoolClassRespTrans create() {
        return new SchoolClassRespTrans();
    }

    @Override
    public SchoolClassResp toTransform(SchoolClass in) {
        if (in == null) {
            return null;
        }

        return SchoolClassResp.create().withId(in.getId())
                .withCourse(CourseRespTrans.create().toTransform(in.getCourse())).withStartDate(in.getStartDate())
                .withEndDate(in.getEndDate()).withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<SchoolClassResp> toTransform(List<SchoolClass> inList) {
        if (inList == null) {
            return null;
        }

        List<SchoolClassResp> outList = new ArrayList<SchoolClassResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
