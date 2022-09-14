package org.pj.metaverse.handle;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pj.metaverse.common.service.CommonService;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.entity.constant.PointInfoConstant;
import org.pj.metaverse.entity.constant.UserLogConstant;
import org.pj.metaverse.entity.vo.MapPointInfoVO;
import org.pj.metaverse.entity.vo.TUserLogVO;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author pengjie
 * @date 15:06 2022/8/29
 **/
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.END_INSTANCE)
@Slf4j
@RequiredArgsConstructor
public class GameTypeEndPointService extends GameTypeHandleCommon{
    private final StringRedisTemplate stringRedisTemplate;
    private final CommonService commonService;


    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx, ChannelPromise promise) {
        MessageRepResult<Void> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(MessageTypeConstant.HEART_BEAT);
        messageRepResult.setMessage("当前关卡结束，结算相关资源");
        // TODO 结算相关资源
        // 获取用户当前关卡信息
        Map<String, Object> data = messageRequest.getData();
        MapPointInfoVO vo = JSON.parseObject(JSON.toJSONString(data), MapPointInfoVO.class);
        // 判断如果是剧情模式，将故事剧情锁消除
        if (PointInfoConstant.Type.STORY.equals(vo.getType())){
            TUserLogVO userLogVO = new TUserLogVO();
            userLogVO.setUserId(messageRequest.getUserId());
            userLogVO.setLogType(UserLogConstant.STORY_PASS);
            userLogVO.setLogData("通过剧情模式");
            commonService.writeLogByUserIdAndLogType(userLogVO);
        }
        messageRepResult.setData(null);
        super.sendMessage(ctx,messageRepResult);
    }
}
