package cn.xiaoge.design.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import cn.xiaoge.design.entity.vo.ReturnBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 * @author 19070
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandleController {


    /**
     * 数据校检异常
     */
    @ExceptionHandler(BindException.class)
    public ReturnBean validExceptionHandle(BindException e) {
        log.error(e.getMessage(), e);
        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 参数校检异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ReturnBean validExceptionHandle(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 数据校检异常,当参数含有注解{@code @RequestBody}时,
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ReturnBean methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, e.getMessage());
    }


    /**
     * 请求参数丢失异常，当方法标记为@RequestParam(require = true)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ReturnBean missingServletRequestParameterExceptionHandle(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ReturnBean.of(ReturnBean.AnswerCode.PARAMETER_ERROR, e.getMessage());
    }

    /**
     * 捕获所有异常
     */
    @ExceptionHandler(Throwable.class)
    public ReturnBean throwableHandle(Throwable e) {
        log.error(e.getMessage(), e);
        return ReturnBean.of(ReturnBean.AnswerCode.UNKNOWN_ERROR, e.getMessage());
    }


}
