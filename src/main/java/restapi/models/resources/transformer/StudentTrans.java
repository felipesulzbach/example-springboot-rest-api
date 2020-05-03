package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Student;
import restapi.models.resources.StudentResp;

/**
 * @author Felipe Sulzbach
 */
public class StudentTrans implements Transformer<StudentResp, Student> {

    private StudentTrans() {
    }

    public static StudentTrans create() {
        return new StudentTrans();
    }

    @Override
    public Student toTransform(StudentResp in) {
        if (in == null) {
            return null;
        }

        return Student.create().withSchoolClass(SchoolClassTrans.create().toTransform(in.getSchoolClass()))
                .withId(in.getId()).withName(in.getName()).withCpf(in.getCpf()).withCellPhone(in.getCellPhone())
                .withCity(in.getCity()).withZipCode(in.getZipCode()).withAddress(in.getAddress())
                .withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<Student> toTransform(List<StudentResp> inList) {
        if (inList == null) {
            return null;
        }

        List<Student> outList = new ArrayList<Student>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
