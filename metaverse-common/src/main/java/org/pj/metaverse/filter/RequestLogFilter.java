package org.pj.metaverse.filter;

import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.constant.ProjectDefaultProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author pengjie
 * @date 9:49 2022/6/9
 **/
@Component
@Slf4j
public class RequestLogFilter implements Filter {
    @Resource
    private ProjectDefaultProperties properties;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            if (properties.isDebug()) {
                HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
                String url = servletRequest1.getRequestURI();
                // 获取请求方式
                String method = servletRequest1.getMethod();
                // 获取请求参数
                String params = servletRequest1.getQueryString();
                log.info("请求地址：{}，请求方式：{}，请求参数：{}", url, method, params);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
