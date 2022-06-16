package org.pj.metaverse.constant;


/**
 * 系统配置的常量
 * @author pengjie
 * @date 14:48 2022/5/17
 **/
public interface SystemDictionaryConstant {
    /**
     * 用户所使用的盐
     * @date 2022/5/17 17:33
     */
    String USER_PASSWORD_SALT_KEY = "USER:PASSWORD_SALT";
    String USER_PASSWORD_SALT_VALUE_KEY = "salt";

    /**
     * 头像库系统配置
     * @date 2022/5/17 17:33
     */
    String SYSTEM_AVATAR_KEY = "SYSTEM:AVATAR";
    String SYSTEM_AVATAR_INIT_VALUE_KEY = "initTime";
    String SYSTEM_AVATAR_VALUE_KEY = "time";
    String SYSTEM_AVATAR_DEFAULT_VALUE_KEY = "default";

    /**
     * 名称库系统配置
     * @date 2022/5/17 17:33
     */
    String SYSTEM_NAME_KEY = "SYSTEM:NAME";
    String SYSTEM_NAME_INIT_VALUE_KEY = "initTime";
    String SYSTEM_NAME_VALUE_KEY = "time";
    String SYSTEM_NAME_DEFAULT_VALUE_KEY = "default";

    /**
     * 默认给用户分配的角色
     * @date 2022/5/17 17:34
     */
    String USER_ROLE_KEY = "USER:ROLE";
    Integer USER_ROLE_WEB_DEFAULT_TYPE = 1;
    Integer USER_ROLE_APP_DEFAULT_TYPE = 2;
    String USER_ROLE_WEB_DEFAULT_VALUE_KEY = "webDefault";
    String USER_ROLE_APP_DEFAULT_VALUE_KEY = "appDefault";
}
