package restapi.util.dataFake.model;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.Course;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope("singleton")
public class CourseDf extends DataFake<Course> {

    private static final long serialVersionUID = 1L;

    public Course getData() {
        return Course.create().withId(DataFakeUtil.getLong(3)).withName(DataFakeUtil.getString(8, false))
                .withDescription(DataFakeUtil.getString(12, false)).withRegistrationDate(DataFakeUtil
                        .getLocalDateTime(LocalDateTime.now().plusDays(1L), LocalDateTime.now().plusDays(8L)));
    }
}