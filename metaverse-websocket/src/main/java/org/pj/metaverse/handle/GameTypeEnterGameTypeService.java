package org.pj.metaverse.handle;

import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.common.service.CommonService;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.constant.rpg.DefaultMapConstant;
import org.pj.metaverse.constant.rpg.UserLogConstant;
import org.pj.metaverse.entity.repvo.TPointMapDetailRepVO;
import org.pj.metaverse.entity.reqvo.MgmtCreateMapPointInfoReqVO;
import org.pj.metaverse.entity.vo.TUserLogVO;
import org.pj.metaverse.init.WebSocketHandler;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.pj.metaverse.utils.IdWorker;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 14:43 2022/8/29
 **/
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.ENTER_GAME)
@RequiredArgsConstructor
@Slf4j
public class GameTypeEnterGameTypeService extends GameTypeHandleCommon{
    private final CommonService commonService;
    private final IdWorker idWorker;

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx) {
        String userId = WebSocketHandler.getClientMap().get(ctx.channel().id().asLongText());
        log.info("玩家{}进入游戏", userId);
        // 判断该玩家是否走完剧情任务
        TUserLogVO logByUserIdAndLogType = commonService.getLogByUserIdAndLogType(userId, UserLogConstant.STORY_PASS);
        if (NvlUtils.isNull(logByUserIdAndLogType)) {
            // 发送进入游戏剧情地图的消息
            MessageRepResult<TPointMapDetailRepVO> messageRepResult = new MessageRepResult<>();
            messageRepResult.setMessageType(MessageTypeConstant.GET_STORY_INFO);
            messageRepResult.setMessage("进入游戏剧情地图");
            // 查询剧情地图信息
            TPointMapDetailRepVO data = commonService.queryMapDetail(DefaultMapConstant.DEFAULT_STORY_MAP_CODE);
            if (NvlUtils.isNull(data)) {
                log.error("剧情地图不存在");
                super.sendMessageFail(ctx, "剧情地图不存在");
                return;
            }
            data.setSessionId(String.valueOf(idWorker.nextId()));
            messageRepResult.setData(data);
            super.sendMessage(ctx, messageRepResult);
        }else {
            // 发送进入游戏主地图的消息
            MessageRepResult<Void> messageRepResult = new MessageRepResult<>();
            messageRepResult.setMessageType(DefaultMapConstant.DEFAULT_MAIN_CITY_MAP_CODE);
            messageRepResult.setMessage("进入游戏主地图");
            // TODO 查询主地图信息
        }
    }
}
