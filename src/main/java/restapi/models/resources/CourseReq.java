package restapi.models.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseReq {

    @NotBlank
    @JsonProperty("name")
    @Size(min = 5, max = 20)
    private String name;
    @NotBlank
    @JsonProperty("description")
    @Size(min = 5, max = 100)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", DESCRIPTION: ");
        strb.append(getDescription());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized CourseReq create() {
        return new CourseReq();
    }

    public CourseReq withName(final String name) {
        this.name = name;
        return this;
    }

    public CourseReq withDescription(final String description) {
        this.description = description;
        return this;
    }
}
