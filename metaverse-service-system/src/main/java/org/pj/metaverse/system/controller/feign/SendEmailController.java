package org.pj.metaverse.system.controller.feign;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.entity.system.reqvo.SendMailReqVO;
import org.pj.metaverse.common.result.DataResult;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author pengjie
 * @date 15:45 2022/5/17
 **/
@Api(hidden = true)
@RestController
@RequiredArgsConstructor
@RequestMapping("mail")
public class SendEmailController {
    private final JavaMailSender mailSender;

    @PostMapping
    public DataResult<Void> sendEmail(@RequestBody SendMailReqVO vo) throws Exception{
        //创建复杂有限发送对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        // 设置收件人邮箱
        messageHelper.setTo(vo.getTo());
        // 设置抄报人邮箱（可以不填写）
        messageHelper.setCc(vo.getCc());
        // 设置密送人邮箱（可以不填写）
        messageHelper.setBcc(vo.getBcc());
        // 设置邮件主题
        messageHelper.setSubject(vo.getSubject());
        // 设置发送时间
        messageHelper.setSentDate(new Date());
        // 设置内容
        if (vo.getType().equals(SendMailReqVO.TYPE_TEXT)){
            messageHelper.setText(vo.getText());
        }else {
            messageHelper.setText(vo.getText(),true);
        }
        mailSender.send(mimeMessage);
        return DataResult.success();
    }
}
