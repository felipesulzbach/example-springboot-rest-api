package restapi.models.resources;

import restapi.models.enumeration.EnumAccessPermission;

/**
 * @autor: Felipe Sulzbach
 */
public class ProfileResp {

    private Long id;
    private String name;
    private String code;
    private EnumAccessPermission accessPermission;

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
        strb.append("ID: ");
        strb.append(getId());
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", CODE: ");
        strb.append(getCode());
        strb.append(", ACCESS_PERMISSION: ");
        strb.append(getAccessPermission());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized ProfileResp create() {
        return new ProfileResp();
    }

    public ProfileResp withId(final Long id) {
        this.id = id;
        return this;
    }

    public ProfileResp withName(final String name) {
        this.name = name;
        return this;
    }

    public ProfileResp withCode(final String code) {
        this.code = code;
        return this;
    }

    public ProfileResp withAccessPermission(final EnumAccessPermission accessPermission) {
        this.accessPermission = accessPermission;
        return this;
    }
}
