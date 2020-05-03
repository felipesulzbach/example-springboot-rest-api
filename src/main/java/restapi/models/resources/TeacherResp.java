package restapi.models.resources;

import java.time.LocalDateTime;

public class TeacherResp extends PersonResp {

    private CourseResp course;

    public CourseResp getCourse() {
        return course;
    }

    public void setCourse(CourseResp course) {
        this.course = course;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(super.toString());
        strb.append(", COURSE: ");
        strb.append(getCourse());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized TeacherResp create() {
        return new TeacherResp();
    }

    public TeacherResp withCourse(final CourseResp course) {
        this.course = course;
        return this;
    }

    @Override
    public TeacherResp withId(final Long id) {
        super.withId(id);
        return this;
    }

    @Override
    public TeacherResp withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public TeacherResp withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public TeacherResp withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public TeacherResp withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public TeacherResp withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public TeacherResp withAddress(final String address) {
        super.withAddress(address);
        return this;
    }

    @Override
    public TeacherResp withRegistrationDate(final LocalDateTime registrationDate) {
        super.withRegistrationDate(registrationDate);
        return this;
    }
}
