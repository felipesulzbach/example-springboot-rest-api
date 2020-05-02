package restapi.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @autor: Felipe Sulzbach
 */
@Entity
@Table(schema = "FS_AUTO", name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
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
    private String cpf;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "FS_AUTO", name = "APP_USER_PERSON",
               inverseForeignKey = @ForeignKey(name = "FK_APP_USER_PERSON"),
               joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "ID"))
    @OrderBy("id ASC")
    private List<AppUser> appUserList;

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

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
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

    public Person withCpf(final String cpf) {
        this.cpf = cpf;
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
