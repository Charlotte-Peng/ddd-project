package org.pj.metaverse.feign;

import org.pj.metaverse.entity.system.reqvo.SendMailReqVO;
import org.pj.metaverse.result.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * @author pengjie
 * @date 14:43 2022/5/17
 **/
@FeignClient(name = "Metaverse-system-service")
public interface SystemFeign {
    /**
     * 根据key获取系统配置
     * @author pengjie
     * @date 2022/5/17 16:00
     * @param key 键
     * @return java.lang.String
     */
    @GetMapping("feign/systemDictionary")
    String getDictionary(@RequestParam String key);

    /**
     * 发送邮件
     * @author pengjie
     * @date 2022/5/17 16:01
     * @param vo 发送邮件的实体
     * @return org.pj.common.entity.result.DataResult<java.lang.Void>
     */
    @PostMapping("mail")
    DataResult<Void> sendEmail(@RequestBody SendMailReqVO vo);

    /**
     * 从系统库中获取随机名字
     * @author pengjie
     * @date 2022/5/17 17:25
     * @param type 类型 1:用户昵称
     * @param num 数量
     * @return java.util.Set<java.lang.String>
     */
    @GetMapping("/feign/baseName/random")
    Set<String> randomlyObtainTheNameOfTheSpecifiedType(@RequestParam Integer type, Integer num);

    /**
     * 从系统库中获取随机头像
     * @author pengjie
     * @date 2022/5/17 17:25
     * @param type 类型 1:用户头像
     * @param num 数量
     * @return java.util.Set<java.lang.String>
     */
    @GetMapping("/feign/baseAvatar/random")
    Set<String> randomlyObtainTheAvatarOfTheSpecifiedType(@RequestParam Integer type, Integer num);
}
