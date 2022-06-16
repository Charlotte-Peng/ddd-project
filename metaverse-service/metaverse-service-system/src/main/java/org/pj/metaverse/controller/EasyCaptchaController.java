package org.pj.metaverse.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.constant.redis.SystemRedisConstant;
import org.pj.metaverse.enums.ResponseEnum;
import org.pj.metaverse.result.DataResult;
import org.pj.metaverse.enums.CodeTypeEnum;
import org.pj.metaverse.service.EasyCaptchaService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author  pengjie
 * @date  16:13 2022/5/8
 **/
@Api(tags = "验证码相关")
@RestController
@RequiredArgsConstructor
@RequestMapping("captcha")
public class EasyCaptchaController {
    private final EasyCaptchaService easyCaptchaService;
    private final StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "算术类型")
    @GetMapping("/captcha1")
    public DataResult<String> getGifCaptcha1(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> captchaValueAndBase64 = easyCaptchaService.getCaptchaValueAndBase64(null);
        String sessionId =  request.getSession().getId();
        stringRedisTemplate.opsForValue().set(SystemRedisConstant.CAPTCHA_KEY+sessionId,captchaValueAndBase64.get("code"),5, TimeUnit.MINUTES);
        return new DataResult<>(ResponseEnum.SUCCESS,captchaValueAndBase64.get("base64"));
    }

    @ApiOperation(value = "gif")
    @GetMapping("/captcha2")
    public Map<String, String> getGifCaptcha2(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.GIF);
    }

    @ApiOperation(value = "png")
    @GetMapping("/captcha3")
    public Map<String, String> getGifCaptcha3(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.SPEC);
    }

    @ApiOperation(value = "chinese文字类型")
    @GetMapping("/captcha4")
    public Map<String, String> getGifCaptcha4(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE);
    }

    @ApiOperation(value = "chinese Gif类型")
    @GetMapping("/captcha5")
    public Map<String, String> getGifCaptcha5(){
        return easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.CHINESE_GIF);
    }

}
