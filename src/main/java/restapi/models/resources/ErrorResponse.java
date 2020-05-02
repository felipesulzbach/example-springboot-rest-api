package restapi.models.resources;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @autor: Felipe Sulzbach
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus status;
    private String developerMessage;
    private String userMessage;
    private String errorCode;
    private String moreInfo;
    private String stackTrace;

    public ErrorResponse() {
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName());
        str.append("[ STATUS: ");
        str.append(getStatus());
        str.append(", DEVELOPER_MESSAGE: '");
        str.append(getDeveloperMessage());
        str.append("', USER_MESSAGE: '");
        str.append(getUserMessage());
        str.append("', ERROR_CODE: ");
        str.append(getErrorCode());
        str.append(", MORE_INFO: ");
        str.append(getMoreInfo());
        str.append(", STACK_TRACE: ");
        str.append(getStackTrace());
        str.append("]");
        return str.toString();
    }

    public Integer getStatus() {
        if (this.status != null && status.value() != 0)
            return status.value();
        else
            return null;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public static synchronized ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse withStatus(final HttpStatus status) {
        this.status = status;
        return this;
    }

    public ErrorResponse withDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    public ErrorResponse withUserMessage(final String userMessage) {
        this.userMessage = userMessage;
        return this;
    }

    public ErrorResponse withErrorCode(final String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ErrorResponse withMoreInfo(final String moreInfo) {
        this.moreInfo = moreInfo;
        return this;
    }

    public ErrorResponse withStackTrace(final String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
}