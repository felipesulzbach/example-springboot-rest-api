package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Person;
import restapi.models.resources.PersonResp;

/**
 * @author Felipe Sulzbach
 */
public class PersonTrans implements Transformer<PersonResp, Person> {

    private PersonTrans() {
    }

    public static PersonTrans create() {
        return new PersonTrans();
    }

    @Override
    public Person toTransform(PersonResp in) {
        if (in == null) {
            return null;
        }

        return Person.create().withId(in.getId()).withName(in.getName()).withCpf(in.getCpf())
                .withCellPhone(in.getCellPhone()).withCity(in.getCity()).withZipCode(in.getZipCode())
                .withAddress(in.getAddress()).withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<Person> toTransform(List<PersonResp> inList) {
        if (inList == null) {
            return null;
        }

        List<Person> outList = new ArrayList<Person>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
