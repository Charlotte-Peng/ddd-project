package org.pj.metaverse.result;

import lombok.Data;
import org.pj.metaverse.enmus.MessageTypeEnum;
import org.wildfly.common.annotation.NotNull;

import java.io.Serializable;
import java.util.Map;

/**
 * @author pengjie
 * @date 10:35 2022/8/23
 **/
@Data
public class MessageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /** socket消息类型 */
    @NotNull
    private String socketMessageType;

    /** 消息id */
    private String messageId;

    /** 消息类型 */
    private MessageTypeEnum messageType;

    /** 消息内容 */
    private String message;

    /** 消息数据 */
    private Map<String,Object> data;

}
