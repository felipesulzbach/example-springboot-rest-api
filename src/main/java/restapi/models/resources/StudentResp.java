package restapi.models.resources;

import java.time.LocalDateTime;

public class StudentResp extends PersonResp {

    private SchoolClassResp schoolClass;

    public SchoolClassResp getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassResp schoolClass) {
        this.schoolClass = schoolClass;
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        strb.append(getClass().getSimpleName());
        strb.append(" [");
        strb.append(super.toString());
        strb.append(", SCHOOL_CLASS: ");
        strb.append(getSchoolClass());
        strb.append("]");
        return strb.toString();
    }

    public static synchronized StudentResp create() {
        return new StudentResp();
    }

    public StudentResp withSchoolClass(final SchoolClassResp schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    @Override
    public StudentResp withId(final Long id) {
        super.withId(id);
        return this;
    }

    @Override
    public StudentResp withName(final String name) {
        super.withName(name);
        return this;
    }

    @Override
    public StudentResp withCpf(final String cpf) {
        super.withCpf(cpf);
        return this;
    }

    @Override
    public StudentResp withCellPhone(final String cellPhone) {
        super.withCellPhone(cellPhone);
        return this;
    }

    @Override
    public StudentResp withCity(final String city) {
        super.withCity(city);
        return this;
    }

    @Override
    public StudentResp withZipCode(final String zipCode) {
        super.withZipCode(zipCode);
        return this;
    }

    @Override
    public StudentResp withAddress(final String address) {
        super.withAddress(address);
        return this;
    }

    @Override
    public StudentResp withRegistrationDate(final LocalDateTime registrationDate) {
        super.withRegistrationDate(registrationDate);
        return this;
    }
}
