package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Course;
import restapi.models.resources.CourseResp;

/**
 * @author felipe.sulzbach
 */
public class CourseRespTrans implements Transformer<Course, CourseResp> {

    private CourseRespTrans() {
    }

    public static CourseRespTrans create() {
        return new CourseRespTrans();
    }

    @Override
    public CourseResp toTransform(Course in) {
        if (in == null) {
            return null;
        }

        return CourseResp.create().withId(in.getId()).withName(in.getName()).withDescription(in.getDescription())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<CourseResp> toTransform(List<Course> inList) {
        if (inList == null) {
            return null;
        }

        List<CourseResp> outList = new ArrayList<CourseResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
