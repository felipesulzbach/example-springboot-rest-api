package restapi.controler;

import static restapi.util.ExceptionUtil.CODE;
import static restapi.util.ExceptionUtil.DEV_MESSAGE;
import static restapi.util.ExceptionUtil.ERROR;
import static restapi.util.ExceptionUtil.MORE_INFO;
import static restapi.util.ExceptionUtil.STATUS;
import static restapi.util.ExceptionUtil.USER_MESSAGE;
import static restapi.util.ExceptionUtil.WARN;
import static restapi.util.ExceptionUtil.getEnumNames;
import static restapi.util.ExceptionUtil.getParameterNames;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.TimeoutException;

import javax.validation.ConstraintViolationException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import restapi.models.resources.ErrorResponse;
import restapi.util.Message;
import restapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@ControllerAdvice
public class ExceptionController {

    private Logger log;
    @Value("${response.stacktrace.size}")
    private Integer limitStackTraceSize;

    @Autowired
    private Message message;

    public ExceptionController() {
        log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    }

    @ExceptionHandler(value = { ServiceException.class })
    public ResponseEntity<ErrorResponse> serviceException(ServiceException e) {
        return loadResponseEntity(e.getCode(), e, WARN, e.getParams());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("INTERNAL_ERROR", e, ERROR);
    }

    @ExceptionHandler(value = { NumberFormatException.class })
    public ResponseEntity<ErrorResponse> numberFormatException(NumberFormatException e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("INTERNAL_ERROR", e, ERROR);
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", e, WARN, e.getParameterName());
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException e) {
        String msg = e.getMessage();
        if (e.getConstraintViolations().iterator().hasNext()) {
            ConstraintViolationImpl<?> cv = (ConstraintViolationImpl<?>) e.getConstraintViolations().iterator().next();
            msg = cv.getMessage();
        }
        return loadResponseEntity("PARAMETER_LIMIT_DIGIT", e, WARN, msg);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        if (e.getCause() instanceof NumberFormatException) {
            return loadResponseEntity("PARAMETER_NOT_NUMERIC", e, WARN, e.getName());
        } else if (e.getCause() instanceof IllegalArgumentException) {
            return loadResponseEntity("PARAMETER_CONVERTED_NOT_EXISTS", e, WARN, e.getCause().getMessage());
        } else {
            return loadResponseEntity("PARAMETER_NOT_INFORMED", e, WARN, e.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        InvalidFormatException ex = (InvalidFormatException) e.getCause();
        if (ex != null && ex.getTargetType().isEnum()) {
            String[] params = { ex.getValue().toString(), getEnumNames((Class<? extends Enum<?>>) ex.getTargetType()) };
            return loadResponseEntity("PARAMETER_NOT_FOUND", e, WARN, (Object[]) params);
        } else {
            log.warn(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.create().withStatus(HttpStatus.BAD_REQUEST).withDeveloperMessage(e.getMessage())
                            .withUserMessage(e.getMessage()).withErrorCode("").withMoreInfo(MORE_INFO));
        }
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return loadResponseEntity("PARAMETER_REQUIRED", e, WARN, getParameterNames(e));
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", e, WARN, e.getMessage());
    }

    @ExceptionHandler(value = { MissingRequestHeaderException.class })
    public ResponseEntity<ErrorResponse> missingRequestHeaderException(MissingRequestHeaderException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", e, WARN, e.getHeaderName());
    }

    @ExceptionHandler(value = { ObjectNotFoundException.class, JpaObjectRetrievalFailureException.class })
    public ResponseEntity<ErrorResponse> objectNotFoundException(ObjectNotFoundException e) {
        return loadResponseEntity("ENTITY_NOT_FOUND", e, WARN);
    }

    @ExceptionHandler(value = { JpaSystemException.class, TransactionTimedOutException.class, TimeoutException.class })
    public ResponseEntity<ErrorResponse> timeOutException(Exception e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("TIMEOUT", e, ERROR);
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        return loadResponseEntity("UNSUPPORTED_METHOD", e, WARN);
    }

    private ResponseEntity<ErrorResponse> loadResponseEntity(String code, Exception ex, String level,
            Object... params) {
        ErrorResponse response = loadErrorResponse(code, ex, params);
        if (WARN.equals(level)) {
            log.warn(response.toString());
        } else if (ERROR.equals(level)) {
            log.error(response.toString());
        }
        return ResponseEntity.status(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS)))).body(response);
    }

    private ResponseEntity<ErrorResponse> loadResponseEntity(String code, Exception ex, String level) {
        ErrorResponse response = loadErrorResponse(code, ex);
        if (WARN.equals(level)) {
            log.warn(response.toString());
        } else {
            log.error(response.toString());
        }
        return ResponseEntity.status(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS)))).body(response);
    }

    private ErrorResponse loadErrorResponse(String code, Exception ex, Object... params) {
        return ErrorResponse.create().withStatus(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS))))
                .withDeveloperMessage(loadMessage(code, DEV_MESSAGE, params))
                .withUserMessage(loadMessage(code, USER_MESSAGE, params)).withErrorCode(loadMessage(code, CODE))
                .withMoreInfo(MORE_INFO).withStackTrace(getStackTrace(ex));
    }

    private ErrorResponse loadErrorResponse(String code, Exception ex) {
        return ErrorResponse.create().withStatus(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS))))
                .withDeveloperMessage(loadMessage(code, DEV_MESSAGE)).withUserMessage(loadMessage(code, USER_MESSAGE))
                .withErrorCode(loadMessage(code, CODE)).withMoreInfo(MORE_INFO).withStackTrace(getStackTrace(ex));
    }

    private String loadMessage(String code, String type, Object[] params) {
        if (params != null) {
            String msg = message.get(code + type, params);
            return msg;
        } else {
            return loadMessage(code, type);
        }
    }

    private String loadMessage(String code, String type) {
        String msg = message.get(code + type);
        return msg;
    }

    private String getStackTrace(Exception ex) {
        if (ex instanceof ServiceException) {
            return null;
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        String response = "";
        final String stackTrace = sw.toString();
        if (stackTrace != null && stackTrace.length() > limitStackTraceSize)
            response = stackTrace.substring(0, limitStackTraceSize) + "...";

        return response;
    }
}