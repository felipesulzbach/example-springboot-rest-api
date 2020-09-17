package restapi.models.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import restapi.models.enumeration.EnumAccessPermission;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileReq {

    @NotBlank
    @JsonProperty("name")
    @Size(min = 5, max = 20)
    private String name;
    @NotBlank
    @JsonProperty("code")
    @Size(min = 5, max = 5)
    private String code;
    @NotNull
    @JsonProperty(value = "accessPermission", required = true)
    private EnumAccessPermission accessPermission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EnumAccessPermission getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(EnumAccessPermission accessPermission) {
        this.accessPermission = accessPermission;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", CODE: ");
        strb.append(getCode());
        strb.append(", ACCESS_PERMISSION: ");
        strb.append(getAccessPermission());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized ProfileReq create() {
        return new ProfileReq();
    }

    public ProfileReq withName(final String name) {
        this.name = name;
        return this;
    }

    public ProfileReq withCode(final String code) {
        this.code = code;
        return this;
    }

    public ProfileReq withAccessPermission(final EnumAccessPermission accessPermission) {
        this.accessPermission = accessPermission;
        return this;
    }
}
