package org.pj.metaverse.handle;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.result.DataResult;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeParseException;

/**
 * @author Administrator
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 对必传参数进行验证
     *
     * @param
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public DataResult<Void> missingServletRequestParameterExceptionHandler(
            MissingServletRequestParameterException e) {
        log.error(e.getMessage(),e);
        return new DataResult<>(500,"参数错误！！请传入参数：" + e.getParameterName());
    }


    /**
     *  自定义异常
     * @param e
     * @return
     * @throws ServerException
     */
    @ExceptionHandler(value = ServerException.class)
    @ResponseBody
    public DataResult<Object> requestExceptionHandler(ServerException e) throws ServerException {
        log.error(e.getMessage(),e);
        return new DataResult<>(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public DataResult<Void> frontEndInformationIsAbnormal(HttpMessageNotReadableException e) throws ServerException {
        log.error(e.getMessage(),e);
        if (e.getCause() instanceof InvalidFormatException invalidFormatException) {
            if (invalidFormatException.getCause() instanceof DateTimeParseException) {
                return new DataResult<>(500,"日期格式错误！请传入正确的格式！");
            }
        }
        return new DataResult<>(DataResult.ERROR_CODE,"前端传入的信息有误！");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public DataResult<Void> exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response){
        // 打印堆栈，以供调试
        log.error("全局异常---------------");
        log.error(e.getMessage());
        e.printStackTrace();
        DataResult<Void> dataResult;
        dataResult = DataResult.error();
        // 返回给前端
        return dataResult;
    }

}
