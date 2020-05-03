package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.SchoolClass;
import restapi.models.resources.SchoolClassResp;

/**
 * @author Felipe Sulzbach
 */
public class SchoolClassTrans implements Transformer<SchoolClassResp, SchoolClass> {

    private SchoolClassTrans() {
    }

    public static SchoolClassTrans create() {
        return new SchoolClassTrans();
    }

    @Override
    public SchoolClass toTransform(SchoolClassResp in) {
        if (in == null) {
            return null;
        }

        return SchoolClass.create().withId(in.getId()).withCourse(CourseTrans.create().toTransform(in.getCourse()))
                .withStartDate(in.getStartDate()).withEndDate(in.getEndDate())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<SchoolClass> toTransform(List<SchoolClassResp> inList) {
        if (inList == null) {
            return null;
        }

        List<SchoolClass> outList = new ArrayList<SchoolClass>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
