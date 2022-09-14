package org.pj.metaverse.handle;

import io.netty.channel.ChannelHandlerContext;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author pengjie
 * @date 15:06 2022/8/29
 **/
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.HEART_BEAT)
public class GameHeartBeatTypeService extends GameTypeHandleCommon{

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx) {
        MessageRepResult<String> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(MessageTypeConstant.HEART_BEAT);
        messageRepResult.setMessage("当前服务器时间");
        messageRepResult.setData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        super.sendMessage(ctx,messageRepResult);
    }
}
