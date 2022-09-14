package org.pj.metaverse.handle;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;
import org.pj.metaverse.common.service.CommonService;
import org.pj.metaverse.constant.MessageTypeConstant;
import org.pj.metaverse.constant.redis.WebSocketRedisConstant;
import org.pj.metaverse.entity.constant.PointInfoConstant;
import org.pj.metaverse.entity.repvo.TPointMapDetailRepVO;
import org.pj.metaverse.entity.reqvo.MapMoveReqVO;
import org.pj.metaverse.entity.vo.MapPointInfoVO;
import org.pj.metaverse.result.MessageRepResult;
import org.pj.metaverse.result.MessageReqResult;
import org.pj.metaverse.utils.NvlUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author pengjie
 * @date 14:43 2022/8/29
 **/
@Component(GameTypeFactory.GAME_TYPE + MessageTypeConstant.MAP_MOVE)
@RequiredArgsConstructor
public class GameTypeMapMoveTypeService extends GameTypeHandleCommon{
    private final StringRedisTemplate stringRedisTemplate;
    private final CommonService commonService;

    @Override
    public void handle(MessageReqResult messageRequest, ChannelHandlerContext ctx, ChannelPromise promise) {
        // 查询该用户是否进入地图
        String format = String.format(WebSocketRedisConstant.Rpg.USER_MAP_KEY, messageRequest.getUserId());
        String s = stringRedisTemplate.opsForValue().get(format);
        MapMoveReqVO mapMoveReqVO = JSON.parseObject(JSON.toJSONString(messageRequest.getData()), MapMoveReqVO.class);
        if (NvlUtils.isNull(s)) {
            // 用户未进入地图，初始化进入地图相关信息
            // 写入当前地图信息
            TPointMapDetailRepVO tPointMapDetailRepVO = commonService.queryMapDetail(mapMoveReqVO.getMapCode());
            stringRedisTemplate.opsForValue().set(format, JSON.toJSONString(tPointMapDetailRepVO));
           s = JSON.toJSONString(tPointMapDetailRepVO);
        }
        // 根据地图信息获取地图详情
        TPointMapDetailRepVO tPointMapDetailRepVO = JSON.parseObject(s, TPointMapDetailRepVO.class);
        // 根据用户移动位置判断是否触发对应的事件
        assert tPointMapDetailRepVO != null;
        int[][] mapPoint = tPointMapDetailRepVO.getMapPoint();
        int eventId = mapPoint[mapMoveReqVO.getY()][mapMoveReqVO.getX()];
        // 设置事件id默认-1，因为事件id对应数组下标对应的事件
        eventId = eventId -1;
        if (eventId == -1){
            // 未触发事件
            MessageRepResult<Void> messageRepResult = new MessageRepResult<>();
            messageRepResult.setMessageType(MessageTypeConstant.MAP_MOVE);
            messageRepResult.setMessage("未触发事件");
            super.sendMessage(ctx, messageRepResult);
            return;
        }
        // 根据事件id获取事件详情
        MapPointInfoVO mapPointInfoVO = tPointMapDetailRepVO.getMapPointInfo().get(eventId);
        // 根据特殊的两个事件id，结束关卡
        if(PointInfoConstant.Type.TRANSPORT.equals(mapPointInfoVO.getType())){
            // 结束关卡
            MessageRepResult<MapPointInfoVO> messageRepResult = new MessageRepResult<>();
            messageRepResult.setMessageType(MessageTypeConstant.END_INSTANCE);
            messageRepResult.setMessage("当前为传送关卡,进入下一关卡");
            messageRepResult.setData(mapPointInfoVO);
            super.sendMessage(ctx, messageRepResult);
            return;
        }
        // 返回事件详情
        MessageRepResult<MapPointInfoVO> messageRepResult = new MessageRepResult<>();
        messageRepResult.setMessageType(MessageTypeConstant.MAP_EVENT);
        messageRepResult.setMessage("触发事件");
        messageRepResult.setData(mapPointInfoVO);
        super.sendMessage(ctx, messageRepResult);
    }
}
