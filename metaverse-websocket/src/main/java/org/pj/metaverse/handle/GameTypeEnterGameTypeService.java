package org.pj.metaverse.handle;

import io.netty.channel.ChannelHandlerContext;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 14:43 2022/8/29
 **/
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.ENTER_GAME)
public class GameTypeEnterGameTypeService extends GameTypeHandleCommon{

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx) {
        MessageRepResult<Void> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(messageRequest.getMessageType());
        messageRepResult.setMessage("进入游戏成功");
        super.sendMessage(messageRepResult, ctx);
    }
}
