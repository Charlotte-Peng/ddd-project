package org.pj.metaverse.enmus;

/**
 * @author pengjie
 * @date 10:37 2022/8/23
 **/
public enum MessageTypeEnum {
    /**
     * 登录
     */
    LOGIN( 1, "login" ),
    /**
     * 登出
     */
    LOGOUT ( 2, "logout" ),
    /**
     * 注册
     */
    REGISTER ( 3, "register" ),
    ;

    private Integer messageCode;
    private String messageType;

    MessageTypeEnum(Integer messageCode, String messageType) {
        this.messageCode = messageCode;
        this.messageType = messageType;
    }
}
