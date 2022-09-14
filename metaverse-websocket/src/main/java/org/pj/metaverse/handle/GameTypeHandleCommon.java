package org.pj.metaverse.handle;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author pengjie
 * @date 14:30 2022/8/29
 **/
@Slf4j
public class GameTypeHandleCommon implements GameTypeHandle {
    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx) {
        ChannelPromise promise = ctx.newPromise();
        this.handle(messageRequest,ctx,promise);
    }

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx, ChannelPromise promise) {

    }

    /**
     * 发送消息出去
     * @author pengjie
     * @date 2022/8/29 14:33
     * @param messageRepResult 消息对象
     * @param ctx 通道相关信息
     */
    public void sendMessage(ChannelHandlerContext ctx,MessageRepResult<?> messageRepResult) {
        this.sendMessage(ctx,messageRepResult,null);
    }
    /**
     * 发送消息出去,并且可以判断是否成功发送出去
     * @author pengjie
     * @date 2022/8/29 14:33
     * @param messageRepResult 消息对象
     * @param ctx 通道相关信息
     */
    public void sendMessage(ChannelHandlerContext ctx,MessageRepResult<?> messageRepResult, ChannelPromise promise) {
        if (promise != null) {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(messageRepResult)),promise.addListener(future -> {
                if (future.isSuccess()) {
                    log.debug("消息发送成功:{}", JSON.toJSONString(messageRepResult));
                } else {
                    log.error("消息发送失败:{},异常:{}", JSON.toJSONString(messageRepResult), future.cause());
                }
            }));
        } else {
            ctx.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(messageRepResult)));
        }

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
