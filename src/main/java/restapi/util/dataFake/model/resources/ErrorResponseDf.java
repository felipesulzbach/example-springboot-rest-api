package restapi.util.dataFake.model.resources;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import restapi.models.resources.ErrorResponse;
import restapi.util.DataFakeUtil;
import restapi.util.dataFake.DataFake;

/**
 * @autor: Felipe Sulzbach
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class ErrorResponseDf extends DataFake<ErrorResponse> {

    private static final long serialVersionUID = 1L;

    @Override
    public ErrorResponse getData() {
        return ErrorResponse.create().withStatus(HttpStatus.MULTIPLE_CHOICES)
                .withMessage(DataFakeUtil.getString(8, false))
                .withErrorCode(DataFakeUtil.getString(5, false).toUpperCase())
                .withMoreInfo(DataFakeUtil.getString(12, false)).withStackTrace(DataFakeUtil.getString(50, true));
    }
}
