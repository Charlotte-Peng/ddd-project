package org.pj.metaverse.entity.system.reqvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author pengjie
 * @date 15:48 2022/5/17
 **/
@Data
public class SendMailReqVO {
    @ApiModelProperty(value = "收件人邮箱",required = true)
    private String to;

    @ApiModelProperty(value = "抄报人邮箱")
    private String cc;

    @ApiModelProperty(value = "抄报人邮箱")
    private String bcc;

    @ApiModelProperty(value = "邮件主题")
    private String subject;

    @ApiModelProperty(value = "内容")
    private String text;

    @ApiModelProperty(value = "类型 0:普通文字 1:html")
    private Integer type;

    public static Integer TYPE_TEXT = 0;
    public static Integer TYPE_HTML = 1;
}
