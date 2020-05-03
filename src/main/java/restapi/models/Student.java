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

import restapi.models.resources.StudentReq;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "STUDENT",
       indexes = { @Index(name = "IDX_STUDENT_SCHOOL_CLASS", columnList = "SCHOOL_CLASS_ID") })
@PrimaryKeyJoinColumn(name = "ID", foreignKey = @ForeignKey(name = "FK_STUDENT_PERSON"))
@DynamicUpdate
public class Student extends Person {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "SCHOOL_CLASS_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_STUDENT_SCHOOL_CLASS"))
    private SchoolClass schoolClass;

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public static Student valueOf(Student entity, StudentReq req, SchoolClass schoolClass) {
        return entity.withSchoolClass(schoolClass).withName(req.getName()).withCpf(req.getCpf())
                .withCellPhone(req.getCellPhone()).withCity(req.getCity()).withZipCode(req.getZipCode())
                .withAddress(req.getAddress()).withRegistrationDate(LocalDateTime.now());
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(super.toString());
        strb.append(", SCHOOL_CLASS: ");
        strb.append(getSchoolClass());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized Student create() {
        return new Student();
    }

    public Student withSchoolClass(final SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    @Override
    public Student withId(final Long id) {
        super.withId(id);
        return this;
    }

    @Override
    public Student withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public Student withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public Student withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public Student withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public Student withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public Student withAddress(final String address) {
        super.withAddress(address);
        return this;
    }

    @Override
    public Student withRegistrationDate(final LocalDateTime registrationDate) {
        super.withRegistrationDate(registrationDate);
        return this;
    }
}
