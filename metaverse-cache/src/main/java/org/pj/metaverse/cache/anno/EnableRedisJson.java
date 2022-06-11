package org.pj.metaverse.cache.anno;

import org.pj.metaverse.cache.config.RedisJsonConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author pengjie
 * @date 15:13 2022/6/1
 **/
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisJsonConfig.class)
public @interface EnableRedisJson {
}
