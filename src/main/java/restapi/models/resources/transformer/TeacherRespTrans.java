package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Teacher;
import restapi.models.resources.TeacherResp;

/**
 * @author felipe.sulzbach
 */
public class TeacherRespTrans implements Transformer<Teacher, TeacherResp> {

    private TeacherRespTrans() {
    }

    public static TeacherRespTrans create() {
        return new TeacherRespTrans();
    }

    @Override
    public TeacherResp toTransform(Teacher in) {
        if (in == null) {
            return null;
        }

        return TeacherResp.create().withCourse(CourseRespTrans.create().toTransform(in.getCourse())).withId(in.getId())
                .withName(in.getName()).withCpf(in.getCpf()).withCellPhone(in.getCellPhone()).withCity(in.getCity())
                .withZipCode(in.getZipCode()).withAddress(in.getAddress())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<TeacherResp> toTransform(List<Teacher> inList) {
        if (inList == null) {
            return null;
        }

        List<TeacherResp> outList = new ArrayList<TeacherResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
