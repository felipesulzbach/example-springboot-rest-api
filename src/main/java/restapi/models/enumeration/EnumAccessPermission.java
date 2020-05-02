package restapi.models.enumeration;

/**
 * @autor: Felipe Sulzbach
 */
public enum EnumAccessPermission {

    NO_ACCESS(
            "No access", "N"
    ), READING(
            "Reading", "R"
    ), WRITING(
            "Writing", "W"
    );

    private String name;
    private String value;

    EnumAccessPermission(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
