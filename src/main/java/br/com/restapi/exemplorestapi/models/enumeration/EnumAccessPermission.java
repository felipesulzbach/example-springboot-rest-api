package br.com.restapi.exemplorestapi.models.enumeration;

/**
 * @autor: Felipe Sulzbach
 */
public enum EnumAccessPermission {

    NO_ACCESS(
            "N", "No access"
    ), READING(
            "R", "Reading"
    ), WRITING(
            "W", "Writing"
    );

    private String code;
    private String name;

    EnumAccessPermission(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
