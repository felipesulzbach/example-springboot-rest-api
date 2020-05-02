package restapi.models;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

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
    @JoinColumn(name = "COURSE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_TEACHER_COURSE"))
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
}
