package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Teacher;
import restapi.models.resources.TeacherResp;

/**
 * @author Felipe Sulzbach
 */
public class TeacherTrans implements Transformer<TeacherResp, Teacher> {

    private TeacherTrans() {
    }

    public static TeacherTrans create() {
        return new TeacherTrans();
    }

    @Override
    public Teacher toTransform(TeacherResp in) {
        if (in == null) {
            return null;
        }

        return Teacher.create().withCourse(CourseTrans.create().toTransform(in.getCourse())).withId(in.getId())
                .withName(in.getName()).withCpf(in.getCpf()).withCellPhone(in.getCellPhone()).withCity(in.getCity())
                .withZipCode(in.getZipCode()).withAddress(in.getAddress())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<Teacher> toTransform(List<TeacherResp> inList) {
        if (inList == null) {
            return null;
        }

        List<Teacher> outList = new ArrayList<Teacher>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
