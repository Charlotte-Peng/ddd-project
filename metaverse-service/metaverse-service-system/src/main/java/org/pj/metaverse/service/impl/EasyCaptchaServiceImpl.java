package org.pj.metaverse.service.impl;

import com.wf.captcha.base.Captcha;
import org.pj.metaverse.enums.CodeTypeEnum;
import org.pj.metaverse.utils.EasyCaptchaProducer;
import org.pj.metaverse.service.EasyCaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengjie
 */
@Service
public class EasyCaptchaServiceImpl implements EasyCaptchaService {
    @Resource
    private EasyCaptchaProducer easyCaptchaProducer;


    @Override
    public Map<String,String> getCaptchaValueAndBase64(CodeTypeEnum codeType){
        Captcha captcha;
        if (codeType == null){
            captcha = easyCaptchaProducer.getCaptcha();
        }else {
            captcha = easyCaptchaProducer.getCaptcha(codeType);
        }
        //1、获取到结果值
        String captchaValue = captcha.text();
        //对于数学类型的需要进行处理
        if (codeType == null || codeType == CodeTypeEnum.ARITHMETIC) {
            //noinspection AlibabaUndefineMagicConstant
            if (captcha.getCharType() - 1 == CodeTypeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
                captchaValue = captchaValue.split("\\.")[0];
            }
        }
        //2、获取到Base64编码
        String captchaBase64 = captcha.toBase64();
        Map<String,String> result = new HashMap<>(2);
        result.put("code", captchaValue);
        result.put("base64", captchaBase64);
        return result;
    }
}
