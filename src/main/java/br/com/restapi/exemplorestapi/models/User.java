package br.com.restapi.exemplorestapi.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(schema = "FS_AUTO", name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "KEY")
    private String key;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "USER_PERSON_FK"))
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        User other = (User) obj;
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
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", KEY: ");
        strb.append(getKey());
        strb.append(", PERSON: ");
        strb.append(getPerson());
        strb.append(", Profile: ");
        strb.append(getProfile());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized User create() {
        return new User();
    }

    public User withId(final Long id) {
        this.id = id;
        return this;
    }

    public User withName(final String name) {
        this.name = name;
        return this;
    }

    public User withKey(final String key) {
        this.key = key;
        return this;
    }

    public User withPerson(final Person person) {
        this.person = person;
        return this;
    }

    public User withProfile(final Profile profile) {
        this.profile = profile;
        return this;
    }
}