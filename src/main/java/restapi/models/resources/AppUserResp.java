package restapi.models.resources;

import java.time.LocalDateTime;

public class AppUserResp {

    private Long id;
    private String name;
    private PersonResp person;
    private ProfileResp profile;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationDate;
    private LocalDateTime expirationDate;

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

    public PersonResp getPerson() {
        return person;
    }

    public void setPerson(PersonResp person) {
        this.person = person;
    }

    public ProfileResp getProfile() {
        return profile;
    }

    public void setProfile(ProfileResp profile) {
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
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

    public static synchronized AppUserResp create() {
        return new AppUserResp();
    }

    public AppUserResp withId(final Long id) {
        this.id = id;
        return this;
    }

    public AppUserResp withName(final String name) {
        this.name = name;
        return this;
    }

    public AppUserResp withPerson(final PersonResp person) {
        this.person = person;
        return this;
    }

    public AppUserResp withProfile(final ProfileResp profile) {
        this.profile = profile;
        return this;
    }

    public AppUserResp withStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public AppUserResp withEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public AppUserResp withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public AppUserResp withExpirationDate(final LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}
