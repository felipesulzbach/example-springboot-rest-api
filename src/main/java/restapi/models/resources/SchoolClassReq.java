package restapi.models.resources;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchoolClassReq {

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
        strb.append("COURSE_ID: ");
        strb.append(getCourseId());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized SchoolClassReq create() {
        return new SchoolClassReq();
    }

    public SchoolClassReq withCourseId(final Long courseId) {
        this.courseId = courseId;
        return this;
    }
}
