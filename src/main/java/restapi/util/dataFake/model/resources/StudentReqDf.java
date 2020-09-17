package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import restapi.models.resources.StudentReq;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class StudentReqDf extends DataFake<StudentReq> {

    private static final long serialVersionUID = 1L;

    @Override
    public StudentReq getData() {
        return StudentReq.create().withName(DataFakeUtil.getString(8, false)).withCpf(DataFakeUtil.getIntegerString(11))
                .withCellPhone(DataFakeUtil.getIntegerString(11)).withCity(DataFakeUtil.getString(8, false))
                .withZipCode(DataFakeUtil.getIntegerString(9)).withAddress(DataFakeUtil.getString(12, true))
                .withSchoolClassId(DataFakeUtil.getLong(3));
    }
}
