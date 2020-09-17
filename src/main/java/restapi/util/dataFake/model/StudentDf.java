package restapi.util.dataFake.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Student;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope("singleton")
public class StudentDf extends DataFake<Student> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private SchoolClassDf schoolClassDf;

    public Student getData() {
        return Student.create().withId(DataFakeUtil.getLong(3)).withName(DataFakeUtil.getString(8, false))
                .withCpf(DataFakeUtil.getIntegerString(11)).withCellPhone(DataFakeUtil.getIntegerString(11))
                .withCity(DataFakeUtil.getString(8, false)).withZipCode(DataFakeUtil.getIntegerString(9))
                .withAddress(DataFakeUtil.getString(12, true)).withRegistrationDate(LocalDateTime.now())
                .withSchoolClass(schoolClassDf.getData());
    }
}