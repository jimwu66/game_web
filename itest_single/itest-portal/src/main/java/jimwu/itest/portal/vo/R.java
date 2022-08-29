package jimwu.itest.portal.vo;

import jimwu.itest.portal.service.ServiceException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NON_AUTHORITATIVE_INFORMATION = 203;
    public static final int NO_CONTENT = 204;
    public static final int REST_CONTENT = 205;
    public static final int PARTIAL_CONTENT = 206;
    public static final int MULTIPLE_CHOICES = 300;
    public static final int MOVED_PERMANENTLY = 301;
    public static final int MOVED_TEMPORARILY = 302;
    public static final int SEE_OTHER = 303;
    public static final int NOT_MODIFIED = 304;
    public static final int USE_PROXY = 305;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PAYMENT_REQUIRED = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int PROXY_AUTHENTICATION = 407;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int GONE = 410;
    public static final int LENGTH_REQUIRED = 411;
    public static final int PRECONDITION_FAILED = 412;
    public static final int REQUEST_ENTITY_TOO_LARGE = 413;
    public static final int REQUEST_URI_TOO_LONG = 414;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int UNPROCESSABLE_ENTITY = 422;
    public static final int INTERNAL_SERVER_ERROR = 500;

    private int code;
    private String message;
    private T data;

    public static R ok(String message){return new R().setCode(OK).setMessage(message);}
    public static R ok(Object data){return new R().setCode(OK).setData(data);}

    public static R created(String message){return new R().setCode(CREATED).setMessage(message);}
    public static R created(Object data){return new R().setCode(CREATED).setData(data);}

    public static R notFound(String message){return new R().setCode(NOT_FOUND).setMessage(message);}
    public static R notFound(Object data){return new R().setCode(NOT_FOUND).setData(data);}

    public static R unprocessableEntity(String message){return new R().setCode(UNPROCESSABLE_ENTITY).setMessage(message);}
    public static R unprocessableEntity(Object data){return new R().setCode(UNPROCESSABLE_ENTITY).setData(data);}

    public static R failed(Throwable e){return new R().setCode(INTERNAL_SERVER_ERROR).setMessage(e.getMessage());}
    public static R failed(ServiceException e){return new R().setCode(INTERNAL_SERVER_ERROR).setData(e);}


}
