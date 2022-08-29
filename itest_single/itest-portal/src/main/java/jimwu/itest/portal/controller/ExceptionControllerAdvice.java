package jimwu.itest.portal.controller;


import jimwu.itest.portal.service.ServiceException;
import jimwu.itest.portal.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public R serviceExceptionHandler(ServiceException e){
        log.error("Service handle error!",e);
        return R.failed(e);
    }
    @ExceptionHandler
    public R exceptionHandler(Exception e){
        log.error("others error!",e);
        return R.failed(e);
    }
}