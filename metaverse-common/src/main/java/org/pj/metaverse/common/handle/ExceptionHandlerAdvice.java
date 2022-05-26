package org.pj.metaverse.common.handle;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.common.enums.ResponseEnum;
import org.pj.metaverse.common.exception.ServerException;
import org.pj.metaverse.common.result.DataResult;
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
        return new DataResult<>(ResponseEnum.PLEASE_CHECK_THE_INCOMING_FORMAT);
    }

    // SaToken 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public DataResult<Void> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response){
        // 打印堆栈，以供调试
        log.error("全局异常---------------");
        log.error(e.getMessage());
        e.printStackTrace();
        DataResult<Void> dataResult;
        if (e instanceof NotLoginException ee) {
            // 如果是未登录异常
            dataResult = new DataResult<>(DataResult.ERROR_CODE,ee.getMessage());
        }
        else if(e instanceof NotRoleException ee) {
            // 如果是角色异常
            dataResult = new DataResult<>(DataResult.ERROR_CODE,"无此角色：" + ee.getRole());
        }
        else if(e instanceof NotPermissionException ee) {
            // 如果是权限异常
            dataResult = new DataResult<>(DataResult.ERROR_CODE,"无此权限：" + ee.getCode());
        }
        else if(e instanceof DisableLoginException ee) {
            // 如果是被封禁异常
            dataResult = new DataResult<>(DataResult.ERROR_CODE,"账号被封禁：" + ee.getDisableTime() + "秒后解封");
        }
        else {	// 普通异常, 输出：500 + 异常信息
            dataResult = DataResult.error();
        }
        // 返回给前端
        return dataResult;
    }

    /*@ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public DataResult<Void> serviceNotFound(RuntimeException e){
        if (e.getCause() instanceof ClientException) {
            ClientException exception = (ClientException) e.getCause();
            log.error(exception.getMessage(),exception);
            String message = exception.getMessage();
            int i = message.lastIndexOf(':');
            if (i != -1){
                String substring = message.substring(i+1);
                return new DataResult<>(500,"服务未找到,服务名"+substring);
            }else {
                log.error(e.getMessage(),e);
                return DataResult.fail();
            }
        }else {
            log.error(e.getMessage(),e);
            return DataResult.fail();
        }
    }*/

}
