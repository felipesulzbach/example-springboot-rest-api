package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Student;
import restapi.models.resources.StudentResp;

/**
 * @author felipe.sulzbach
 */
public class StudentRespTrans implements Transformer<Student, StudentResp> {

    private StudentRespTrans() {
    }

    public static StudentRespTrans create() {
        return new StudentRespTrans();
    }

    @Override
    public StudentResp toTransform(Student in) {
        if (in == null) {
            return null;
        }

        return StudentResp.create().withSchoolClass(SchoolClassRespTrans.create().toTransform(in.getSchoolClass()))
                .withId(in.getId()).withName(in.getName()).withCpf(in.getCpf()).withCellPhone(in.getCellPhone())
                .withCity(in.getCity()).withZipCode(in.getZipCode()).withAddress(in.getAddress())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<StudentResp> toTransform(List<Student> inList) {
        if (inList == null) {
            return null;
        }

        List<StudentResp> outList = new ArrayList<StudentResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
