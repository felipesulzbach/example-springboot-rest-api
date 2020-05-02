package restapi.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author Felipe Sulzbach
 */
public class ExceptionUtil {

    public static final String ERROR = "ERROR";
    public static final String WARN = "WARN";
    public static final String CODE = ".cod";
    public static final String STATUS = ".stt";
    public static final String DEV_MESSAGE = ".dev";
    public static final String USER_MESSAGE = ".msg";
    public static final String MORE_INFO = "https://github.com/felipesulzbach/example-springboot-rest-api/blob/master/README.md";

    public static String getParameterNames(MethodArgumentNotValidException ex) {
        StringBuilder paramNames = new StringBuilder();
        int total = ex.getBindingResult().getFieldErrors().size();
        int count = 0;
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            paramNames.append(error.getField());
            if (total > 1) {
                count++;
                if (count == (total - 1))
                    paramNames.append(" and ");
                else if (count != total)
                    paramNames.append(", ");
            }
        }

        return paramNames.toString();
    }

    public static String getEnumNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).collect(Collectors.joining(", "));
    }
}