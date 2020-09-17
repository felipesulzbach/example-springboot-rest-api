package restapi.models.resources;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentReq extends PersonReq {

    @NotNull
    @JsonProperty("schoolClassId")
    private Long schoolClassId;

    public Long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(Long schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(super.toString());
        strb.append(", SCHOOL_CLASS_ID: ");
        strb.append(getSchoolClassId());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized StudentReq create() {
        return new StudentReq();
    }

    @Override
    public StudentReq withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public StudentReq withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public StudentReq withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public StudentReq withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public StudentReq withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public StudentReq withAddress(final String address) {
        super.withAddress(address);
        return this;
    }

    public StudentReq withSchoolClassId(final Long schoolClassId) {
        this.schoolClassId = schoolClassId;
        return this;
    }
}
