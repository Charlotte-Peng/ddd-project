package org.pj.metaverse.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
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
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.EXIT_GAME)
public class GameTypeExitGameService extends GameTypeHandleCommon{

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx, ChannelPromise promise) {
        MessageRepResult<String> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(MessageTypeConstant.EXIT_GAME);
        messageRepResult.setMessage("退出成功");
        messageRepResult.setData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        super.sendMessage(ctx,messageRepResult);
        ctx.close();
    }
}
