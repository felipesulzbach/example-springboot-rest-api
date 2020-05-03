package restapi.models.resources;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserReq {

    @NotBlank
    @JsonProperty("name")
    @Size(min = 5, max = 20)
    private String name;
    @NotBlank
    @JsonProperty("password")
    @Size(min = 6, max = 10)
    private String password;
    @JsonProperty("personId")
    private Long personId;
    @NotNull
    @JsonProperty("profileId")
    private Long profileId;
    @JsonProperty("expirationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationDate;

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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
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
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", PASSWORD: ");
        strb.append(getPassword());
        strb.append(", PERSON_ID: ");
        strb.append(getPersonId());
        strb.append(", PROFILE_ID: ");
        strb.append(getProfileId());
        strb.append(", EXPIRATION_DATE: ");
        strb.append(getExpirationDate());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized AppUserReq create() {
        return new AppUserReq();
    }

    public AppUserReq withName(final String name) {
        this.name = name;
        return this;
    }

    public AppUserReq withPassword(final String password) {
        this.password = password;
        return this;
    }

    public AppUserReq withPersonId(final Long personId) {
        this.personId = personId;
        return this;
    }

    public AppUserReq withProfileId(final Long profileId) {
        this.profileId = profileId;
        return this;
    }

    public AppUserReq withExpirationDate(final LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}
