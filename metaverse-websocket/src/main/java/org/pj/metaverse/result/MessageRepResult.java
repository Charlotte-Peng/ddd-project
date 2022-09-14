package org.pj.metaverse.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author pengjie
 * @date 10:35 2022/8/23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRepResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 消息类型 */
    private String messageType;

    /** 消息内容 */
    private String message;

    /** 消息数据 */
    private T data;

}
