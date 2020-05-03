package restapi.models.resources.transformer;

import java.util.ArrayList;
import java.util.List;

import restapi.models.Person;
import restapi.models.resources.PersonResp;

/**
 * @author felipe.sulzbach
 */
public class PersonRespTrans implements Transformer<Person, PersonResp> {

    private PersonRespTrans() {
    }

    public static PersonRespTrans create() {
        return new PersonRespTrans();
    }

    @Override
    public PersonResp toTransform(Person in) {
        if (in == null) {
            return null;
        }

        return PersonResp.create().withId(in.getId()).withName(in.getName()).withCpf(in.getCpf())
                .withCellPhone(in.getCellPhone()).withCity(in.getCity()).withZipCode(in.getZipCode())
                .withAddress(in.getAddress()).withRegistrationDate(in.getRegistrationDate());
    }

    @Override
    public List<PersonResp> toTransform(List<Person> inList) {
        if (inList == null) {
            return null;
        }

        List<PersonResp> outList = new ArrayList<PersonResp>();
        inList.forEach(in -> outList.add(this.toTransform(in)));

        return outList;
    }
}
