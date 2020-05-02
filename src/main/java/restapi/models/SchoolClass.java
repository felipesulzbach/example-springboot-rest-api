package restapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "SCHOOL_CLASS",
       indexes = { @Index(name = "IDX_SCHOOL_CLASS_COURSE", columnList = "COURSE_ID") })
public class SchoolClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_CLASS_SEQ")
    @SequenceGenerator(schema = "FS_AUTO", name = "SCHOOL_CLASS_SEQ", sequenceName = "SCHOOL_CLASS_SEQ",
                       allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_SCHOOL_CLASS_COURSE"))
    private Course course;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SchoolClass other = (SchoolClass) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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

    public static synchronized SchoolClass create() {
        return new SchoolClass();
    }

    public SchoolClass withId(final Long id) {
        this.id = id;
        return this;
    }

    public SchoolClass withCourse(final Course course) {
        this.course = course;
        return this;
    }

    public SchoolClass withStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SchoolClass withEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public SchoolClass withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
