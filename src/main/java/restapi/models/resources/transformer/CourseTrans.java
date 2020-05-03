package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Course;
import restapi.models.resources.CourseResp;

/**
 * @author Felipe Sulzbach
 */
public class CourseTrans implements Transformer<CourseResp, Course> {

    private CourseTrans() {
    }

    public static CourseTrans create() {
        return new CourseTrans();
    }

    @Override
    public Course toTransform(CourseResp in) {
        if (in == null) {
            return null;
        }

        return Course.create().withId(in.getId()).withName(in.getName()).withDescription(in.getDescription())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<Course> toTransform(List<CourseResp> inList) {
        if (inList == null) {
            return null;
        }

        List<Course> outList = new ArrayList<Course>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
