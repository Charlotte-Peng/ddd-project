package org.pj.metaverse.handle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pj.metaverse.anno.NotControllerResponseAdvice;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.exception.ServerException;
import org.pj.metaverse.result.DataResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.rmi.server.ServerCloneException;

/**
 * @author pengjie
 * @date 9:20 2022/7/14
 **/
@RestControllerAdvice(basePackages = {

        "org.pj.metaverse" })
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {


        // response是DataResult类型，或者注释了NotControllerResponseAdvice都不进行包装
        return !(methodParameter.getParameterType().isAssignableFrom(DataResult.class)
                || methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(body);
            } catch (JsonProcessingException e) {
                throw new ServerException(ResponseEnum.SYSTEM_ERROR, e.getMessage());
            }
        }
        return body;
    }
}
