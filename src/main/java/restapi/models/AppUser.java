package restapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import restapi.models.resources.AppUserReq;
import restapi.util.Cripto;
import restapi.util.Cripto.EnumHash;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "APP_USER",
       indexes = { @Index(name = "IDX_APP_USER_PROFILE", columnList = "PROFILE_ID"),
               @Index(name = "IDX_APP_USER_PERSON", columnList = "PERSON_ID") })
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_SEQ")
    @SequenceGenerator(schema = "FS_AUTO", name = "APP_USER_SEQ", sequenceName = "APP_USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_APP_USER_PERSON"))
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_APP_USER_PROFILE"))
    private Profile profile;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static AppUser valueOf(AppUser entity, AppUserReq req, Person person, Profile profile)
            throws ServiceException {
        return entity.withName(req.getName()).withPassword(Cripto.encript(req.getPassword(), EnumHash.SHA_256))
                .withPerson(person).withProfile(profile).withStartDate(LocalDateTime.now())
                .withEndDate(req.getExpirationDate());
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
        AppUser other = (AppUser) obj;
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
        strb.append(", PASSWORD: ");
        strb.append(getPassword());
        strb.append(", PERSON: ");
        strb.append(getPerson());
        strb.append(", PROFILE: ");
        strb.append(getProfile());
        strb.append(", START_DATE: ");
        strb.append(getStartDate());
        strb.append(", END_DATE: ");
        strb.append(getEndDate());
        strb.append(", REGISTRATION_DATE: ");
        strb.append(getRegistrationDate());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized AppUser create() {
        return new AppUser();
    }

    public AppUser withId(final Long id) {
        this.id = id;
        return this;
    }

    public AppUser withName(final String name) {
        this.name = name;
        return this;
    }

    public AppUser withPassword(final String password) {
        this.password = password;
        return this;
    }

    public AppUser withPerson(final Person person) {
        this.person = person;
        return this;
    }

    public AppUser withProfile(final Profile profile) {
        this.profile = profile;
        return this;
    }

    public AppUser withStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public AppUser withEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public AppUser withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}