package br.com.restapi.exemplorestapi.controler;

import static br.com.restapi.exemplorestapi.util.ExceptionUtil.CODE;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.DEV_MESSAGE;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.ERROR;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.MORE_INFO;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.STATUS;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.USER_MESSAGE;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.WARN;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.getEnumNames;
import static br.com.restapi.exemplorestapi.util.ExceptionUtil.getParameterNames;

import java.util.concurrent.TimeoutException;

import javax.validation.ConstraintViolationException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.restapi.exemplorestapi.models.resources.ErrorResponse;
import br.com.restapi.exemplorestapi.util.Message;
import br.com.restapi.exemplorestapi.util.ServiceException;

/**
 * @autor: Felipe Sulzbach
 */
@ControllerAdvice
public class ExceptionController {

    private Logger log;

    @Autowired
    private Message message;

    public ExceptionController() {
        log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("INTERNAL_ERROR", ERROR);
    }

    @ExceptionHandler(value = { NumberFormatException.class })
    public ResponseEntity<ErrorResponse> numberFormatException(NumberFormatException e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("INTERNAL_ERROR", ERROR);
    }

    @ExceptionHandler(value = { JpaSystemException.class, TransactionTimedOutException.class, TimeoutException.class })
    public ResponseEntity<ErrorResponse> timeOutException(Exception e) {
        log.error(e.getMessage(), e);
        return loadResponseEntity("TIMEOUT", ERROR);
    }

    @ExceptionHandler(value = { ServiceException.class })
    public ResponseEntity<ErrorResponse> serviceException(ServiceException e) {
        return loadResponseEntity(e.getCode(), WARN, e.getParams());
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<ErrorResponse> missingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", WARN, e.getParameterName());
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> constraintViolationException(ConstraintViolationException e) {
        String msg = e.getMessage();
        if (e.getConstraintViolations().iterator().hasNext()) {
            ConstraintViolationImpl<?> cv = (ConstraintViolationImpl<?>) e.getConstraintViolations().iterator().next();
            msg = cv.getMessage();
        }
        return loadResponseEntity("PARAMETER_LIMIT_DIGIT", WARN, msg);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        if (e.getCause() instanceof NumberFormatException) {
            return loadResponseEntity("PARAMETER_NOT_NUMERIC", WARN, e.getName());
        } else if (e.getCause() instanceof IllegalArgumentException) {
            return loadResponseEntity("PARAMETER_CONVERTED_NOT_EXISTS", WARN, e.getCause().getMessage());
        } else {
            return loadResponseEntity("PARAMETER_NOT_INFORMED", WARN, e.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        InvalidFormatException ex = (InvalidFormatException) e.getCause();
        if (ex.getTargetType().isEnum()) {
            String[] params = { ex.getValue().toString(), getEnumNames((Class<? extends Enum<?>>) ex.getTargetType()) };
            return loadResponseEntity("PARAMETER_NOT_FOUND", WARN, (Object[]) params);
        } else {
            log.warn(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.create().withStatus(HttpStatus.BAD_REQUEST)
                            .withDeveloperMessage(ex.getOriginalMessage()).withUserMessage(ex.getOriginalMessage())
                            .withErrorCode("").withMoreInfo(MORE_INFO));
        }
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return loadResponseEntity("PARAMETER_REQUIRED", WARN, getParameterNames(e));
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", WARN, e.getMessage());
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        return loadResponseEntity("UNSUPPORTED_METHOD", WARN);
    }

    @ExceptionHandler(value = { ObjectNotFoundException.class, JpaObjectRetrievalFailureException.class })
    public ResponseEntity<ErrorResponse> objectNotFoundException(ObjectNotFoundException e) {
        return loadResponseEntity("ENTITY_NOT_FOUND", WARN);
    }

    @ExceptionHandler(value = { MissingRequestHeaderException.class })
    public ResponseEntity<ErrorResponse> missingRequestHeaderException(MissingRequestHeaderException e) {
        return loadResponseEntity("PARAMETER_NOT_INFORMED", WARN, e.getHeaderName());
    }

    private ResponseEntity<ErrorResponse> loadResponseEntity(String code, String level, Object... params) {
        ErrorResponse response = loadErrorResponse(code, params);
        if (WARN.equals(level)) {
            log.warn(response.toString());
        } else if (ERROR.equals(level)) {
            log.error(response.toString());
        }
        return ResponseEntity.status(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS)))).body(response);
    }

    private ResponseEntity<ErrorResponse> loadResponseEntity(String code, String level) {
        ErrorResponse response = loadErrorResponse(code);
        if (WARN.equals(level)) {
            log.warn(response.toString());
        } else {
            log.error(response.toString());
        }
        return ResponseEntity.status(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS)))).body(response);
    }

    private ErrorResponse loadErrorResponse(String code, Object... params) {
        return ErrorResponse.create().withStatus(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS))))
                .withDeveloperMessage(loadMessage(code, DEV_MESSAGE, params))
                .withUserMessage(loadMessage(code, USER_MESSAGE, params)).withErrorCode(loadMessage(code, CODE))
                .withMoreInfo(MORE_INFO);
    }

    private ErrorResponse loadErrorResponse(String code) {
        return ErrorResponse.create().withStatus(HttpStatus.valueOf(Integer.valueOf(loadMessage(code, STATUS))))
                .withDeveloperMessage(loadMessage(code, DEV_MESSAGE)).withUserMessage(loadMessage(code, USER_MESSAGE))
                .withErrorCode(loadMessage(code, CODE)).withMoreInfo(MORE_INFO);
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
}