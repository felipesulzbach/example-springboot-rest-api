package restapi.util.dataFake.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.SchoolClass;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope("singleton")
public class SchoolClassDf extends DataFake<SchoolClass> {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CourseDf courseDf;

    public SchoolClass getData() {
        return SchoolClass.create().withId(DataFakeUtil.getLong(3)).withCourse(courseDf.getData())
                .withStartDate(LocalDateTime.now()).withEndDate(DataFakeUtil
                        .getLocalDateTime(LocalDateTime.now().plusDays(1L), LocalDateTime.now().plusDays(8L)))
                .withRegistrationDate(LocalDateTime.now());
    }
}