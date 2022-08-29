package jimwu.itest.portal.service;

import jimwu.itest.portal.vo.R;

public class ServiceException extends RuntimeException{

    private int code = R.INTERNAL_SERVER_ERROR;

    public ServiceException() {}
    public ServiceException(String message) {super(message);}
    public ServiceException(String message, Throwable cause) {super(message, cause);}
    public ServiceException(Throwable cause) {super(cause);}
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public ServiceException(int code) {this.code = code;}
    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }
    public ServiceException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }
    public ServiceException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
    public static ServiceException notFound(String message){
        return new ServiceException(message,R.NOT_FOUND);
    }
    public static ServiceException unprocessableEntity(String message){
        return new ServiceException(message,R.UNPROCESSABLE_ENTITY);
    }
    public static ServiceException badRequest(String message){
        return new ServiceException(message,R.BAD_REQUEST);
    }
    public static ServiceException failed(String message){
        return new ServiceException(message,R.INTERNAL_SERVER_ERROR);
    }
}
