package restapi.models.resources;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherReq extends PersonReq {

    @NotNull
    @JsonProperty("courseId")
    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(super.toString());
        strb.append(", COURSE_ID: ");
        strb.append(getCourseId());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized TeacherReq create() {
        return new TeacherReq();
    }

    public TeacherReq withCourseId(final Long courseId) {
        this.courseId = courseId;
        return this;
    }

    @Override
    public TeacherReq withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public TeacherReq withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public TeacherReq withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public TeacherReq withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public TeacherReq withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public TeacherReq withAddress(final String address) {
        super.withAddress(address);
        return this;
    }
}
