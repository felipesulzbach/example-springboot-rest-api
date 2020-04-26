package br.com.restapi.exemplorestapi.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TEACHER")
@PrimaryKeyJoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
@DynamicUpdate
public class Teacher extends Person {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "id")
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
