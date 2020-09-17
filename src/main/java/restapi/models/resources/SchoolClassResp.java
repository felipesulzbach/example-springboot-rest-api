package restapi.models.resources;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @autor: Felipe Sulzbach
 */
public class SchoolClassResp {

    private Long id;
    private CourseResp course;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseResp getCourse() {
        return course;
    }

    public void setCourse(CourseResp course) {
        this.course = course;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append("ID: ");
        strb.append(getId());
        strb.append(", COURSE: ");
        strb.append(getCourse());
        strb.append(", START_DATE: ");
        strb.append(getStartDate());
        strb.append(", END_DATE: ");
        strb.append(getEndDate());
        strb.append(", REGISTRATION_DATE: ");
        strb.append(getRegistrationDate());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized SchoolClassResp create() {
        return new SchoolClassResp();
    }

    public SchoolClassResp withId(final Long id) {
        this.id = id;
        return this;
    }

    public SchoolClassResp withCourse(final CourseResp course) {
        this.course = course;
        return this;
    }

    public SchoolClassResp withStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SchoolClassResp withEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public SchoolClassResp withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
