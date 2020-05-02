package restapi.util;

/**
 * @autor: Felipe Sulzbach
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;
    private Object[] params;

    private ServiceException(String userMessage) {
        super(userMessage);
    }

    public static ServiceException get(String codMessage, Object... params) {
        return ServiceException.create(codMessage).withCode(codMessage).withParams(params);
    }

    public static ServiceException get(String codMessage) {
        return ServiceException.create(codMessage).withCode(codMessage);
    }

    public String getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }

    public static synchronized ServiceException create(final String userMessage) {
        return new ServiceException(userMessage);
    }

    public ServiceException withCode(final String code) {
        this.code = code;
        return this;
    }

    public ServiceException withParams(final Object[] params) {
        this.params = params;
        return this;
    }
}