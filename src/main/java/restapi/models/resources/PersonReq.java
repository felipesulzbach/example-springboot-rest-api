package restapi.models.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonReq {

    @NotBlank
    @JsonProperty("name")
    @Size(min = 5, max = 50)
    private String name;
    @NotBlank
    @JsonProperty("cpf")
    @Size(min = 6, max = 11)
    private String cpf;
    @NotBlank
    @JsonProperty("cellPhone")
    private String cellPhone;
    @NotBlank
    @JsonProperty("city")
    private String city;
    @NotBlank
    @JsonProperty("zipCode")
    private String zipCode;
    @NotBlank
    @JsonProperty("address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(", NAME: ");
        strb.append(getName());
        strb.append(", CPF: ");
        strb.append(getCpf());
        strb.append(", CELL_PHONE: ");
        strb.append(getCellPhone());
        strb.append(", CITY: ");
        strb.append(getCity());
        strb.append(", ZIP_CODE: ");
        strb.append(getZipCode());
        strb.append(", ADDRESS: ");
        strb.append(getAddress());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized PersonReq create() {
        return new PersonReq();
    }

    public PersonReq withName(final String name) {
        this.name = name;
        return this;
    }

    public PersonReq withCpf(final String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PersonReq withCellPhone(final String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public PersonReq withCity(final String city) {
        this.city = city;
        return this;
    }

    public PersonReq withZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public PersonReq withAddress(final String address) {
        this.address = address;
        return this;
    }
}
