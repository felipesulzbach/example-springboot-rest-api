package restapi.util.dataFake.model;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Person;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope("singleton")
public class PersonDf extends DataFake<Person> {

    private static final long serialVersionUID = 1L;

    public Person getData() {
        return Person.create().withId(DataFakeUtil.getLong(3)).withName(DataFakeUtil.getString(8, false))
                .withCpf(DataFakeUtil.getIntegerString(11)).withCellPhone(DataFakeUtil.getIntegerString(11))
                .withCity(DataFakeUtil.getString(8, false)).withZipCode(DataFakeUtil.getIntegerString(9))
                .withAddress(DataFakeUtil.getString(12, true)).withRegistrationDate(LocalDateTime.now());
    }
}