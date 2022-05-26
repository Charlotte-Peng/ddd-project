package org.pj.metaverse.common.enums;


import org.pj.metaverse.common.result.DataResult;

/**
 * Description:
 *  这是专给响应用的枚举类
 * @author pj
 * @date 2021/5/8/008 13:49
 */
public enum ResponseEnum {

    /**
     * Here is the relevant information of the enumeration
     */
    SYSTEM_ERROR(DataResult.ERROR_CODE,DataResult.ERROR_MESSAGE),
    SUCCESS(DataResult.SUCCESS_CODE,DataResult.SUCCESS_MESSAGE),
    INSUFFICIENT_PERMISSIONS(997,"权限不足！"),
    DATA_DOES_NOT_EXIST(998,"数据不存在！"),
    THE_PARAMETER_IS_WRONG(999,"参数有误"),
    PLEASE_CHECK_THE_INCOMING_FORMAT(800,"请检查传入的数据格式"),
    failedToGetTenantID(801,"无法获取租户id"),


    /**
     * 用户相关
     * @author pengjie
     * @date 2022/5/17 14:57
     *
     */
    WRONG_ACCOUNT_OR_PASSWORD(10000,"账号或密码错误"),
    INCORRECT_VERIFICATION_CODE(10001,"验证码不正确"),
    THIS_ACCOUNT_HAS_BEEN_REGISTERED(10002,"该账号已被注册"),

    /**
     *  AlibabaSentinel相关配置
     */
    // 接口被限流
    THE_INTERFACE_IS_CURRENT_LIMITED(100000,"别请求那么多次嘛,慢点慢点"),
    // 接口被熔断
    THE_INTERFACE_IS_BLOWN(100001,"你看你，都把我玩坏了"),

    ;
    /**
     * Here is the response status code of the enumeration class
     */
    private Integer code;
    /**
     * here is the enumeration parameter of information
     */
    private String message;

     ResponseEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
