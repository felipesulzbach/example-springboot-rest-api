package br.com.restapi.exemplorestapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(schema = "FS_AUTO", name = "PERSON_SEQ", sequenceName = "PERSON_SEQ", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF")
    private String Cpf;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "REGISTRATION_DATE")
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
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
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

    public static synchronized Person create() {
        return new Person();
    }

    public Person withId(final Long id) {
        this.id = id;
        return this;
    }

    public Person withName(final String name) {
        this.name = name;
        return this;
    }

    public Person withCpf(final String Cpf) {
        this.Cpf = Cpf;
        return this;
    }

    public Person withCellPhone(final String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public Person withCity(final String city) {
        this.city = city;
        return this;
    }

    public Person withZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public Person withAddress(final String address) {
        this.address = address;
        return this;
    }

    public Person withRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
