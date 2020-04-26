package br.com.restapi.exemplorestapi.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
@DynamicUpdate
public class Student extends Person {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID", referencedColumnName = "id")
    private SchoolClass schoolClass;

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append("ID: ");
        strb.append(getId());
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
}
