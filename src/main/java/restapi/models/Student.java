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
