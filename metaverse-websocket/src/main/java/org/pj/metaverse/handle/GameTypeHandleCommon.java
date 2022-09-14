package org.pj.metaverse.handle;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author pengjie
 * @date 14:30 2022/8/29
 **/
public class GameTypeHandleCommon implements GameTypeHandle {
    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx) {

    }
    /**
     * 发送消息出去
     * @author pengjie
     * @date 2022/8/29 14:33
     * @param messageRepResult 消息对象
     * @param ctx 通道相关信息
     */
    public void sendMessage(ChannelHandlerContext ctx,MessageRepResult<?> messageRepResult) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(messageRepResult)));
    }
    /**
     * 发送报错消息出去
     * @param ctx 通道相关信息
     * @param message 消息
     */
    public void sendMessageFail(ChannelHandlerContext ctx, String message) {
        MessageRepResult<Void> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(MessageTypeConstant.ERROR);
        messageRepResult.setMessage(message);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(messageRepResult)));
    }
}
