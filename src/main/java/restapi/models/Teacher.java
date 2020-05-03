package restapi.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import restapi.models.resources.TeacherReq;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "TEACHER",
       indexes = { @Index(name = "IDX_TEACHER_COURSE", columnList = "COURSE_ID") })
@PrimaryKeyJoinColumn(name = "ID", foreignKey = @ForeignKey(name = "FK_TEACHER_PERSON"))
@DynamicUpdate
public class Teacher extends Person {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_TEACHER_COURSE"))
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Teacher valueOf(Teacher entity, TeacherReq req, Course course) {
        return entity.withCourse(course).withName(req.getName()).withCpf(req.getCpf()).withCellPhone(req.getCellPhone())
                .withCity(req.getCity()).withZipCode(req.getZipCode()).withAddress(req.getAddress())
                .withRegistrationDate(LocalDateTime.now());
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

    public static synchronized Teacher create() {
        return new Teacher();
    }

    public Teacher withCourse(final Course course) {
        this.course = course;
        return this;
    }

    @Override
    public Teacher withId(final Long id) {
        super.withId(id);
        return this;
    }

    @Override
    public Teacher withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public Teacher withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public Teacher withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public Teacher withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public Teacher withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public Teacher withAddress(final String address) {
        super.withAddress(address);
        return this;
    }

    @Override
    public Teacher withRegistrationDate(final LocalDateTime registrationDate) {
        super.withRegistrationDate(registrationDate);
        return this;
    }
}
