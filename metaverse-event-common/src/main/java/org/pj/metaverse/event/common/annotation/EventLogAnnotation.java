package org.pj.metaverse.event.common.annotation;

import org.pj.metaverse.event.common.enums.EventEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pengjie
 * @date 16:17 2022/5/25
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventLogAnnotation {
   EventEnum EVENT_ENUM() default EventEnum.OTHER;
}
