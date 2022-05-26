package org.pj.metaverse.system.service;

import org.pj.metaverse.system.enums.CodeTypeEnum;

import java.util.Map;

/**
 * @author pengjie
 */
public interface EasyCaptchaService {
    /**
     * 获取指定类型的验证码结果以及Base64编码值
     * @param codeType 验证码类型
     * @return 值code 图片base64
     */
    Map<String,String> getCaptchaValueAndBase64(CodeTypeEnum codeType);
}
