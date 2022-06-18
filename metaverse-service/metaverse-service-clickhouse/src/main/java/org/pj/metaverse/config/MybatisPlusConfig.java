package org.pj.metaverse.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.pj.metaverse.constant.ProjectDefaultPoperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author pengjie
 * @date 10:11 2022/5/25
 **/
@Configuration
public class MybatisPlusConfig {

    @Resource
    ProjectDefaultPoperties projectDefaultPoperties;
    /**
     * 新多租户插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.CLICK_HOUSE));
        return interceptor;
    }
}
