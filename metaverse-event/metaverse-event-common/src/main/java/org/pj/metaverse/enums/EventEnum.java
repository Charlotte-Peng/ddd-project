package org.pj.metaverse.enums;

/**
 * @author pengjie
 * @date 15:16 2022/5/25
 **/
public enum EventEnum {
    /**
     * 日志专用枚举类
     * @date 2022/5/25 15:18
     */
    SYSTEM(1,"系统日志"),
    USER_LOGIN(2,"用户-登录日志"),
    OTHER(0,"其他日志"),
    ;
    public final Integer code;
    public final String notes;

    EventEnum(Integer code, String notes) {
        this.code = code;
        this.notes = notes;
    }
}
