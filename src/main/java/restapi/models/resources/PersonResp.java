package restapi.models.resources;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonResp {

    private Long id;
    private String name;
    private String cpf;
    private String cellPhone;
    private String city;
    private String zipCode;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
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
        strb.append(", REGISTRATION_DATE: ");
        strb.append(getRegistrationDate());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized PersonResp create() {
        return new PersonResp();
    }

    public PersonResp withId(final Long id) {
        this.id = id;
        return this;
    }

    public PersonResp withName(final String name) {
        this.name = name;
        return this;
    }

    public PersonResp withCpf(final String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PersonResp withCellPhone(final String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public PersonResp withCity(final String city) {
        this.city = city;
        return this;
    }

    public PersonResp withZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public PersonResp withAddress(final String address) {
        this.address = address;
        return this;
    }

    public PersonResp withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
