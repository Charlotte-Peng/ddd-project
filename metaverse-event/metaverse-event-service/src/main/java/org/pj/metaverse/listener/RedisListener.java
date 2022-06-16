package org.pj.metaverse.listener;

import com.alibaba.fastjson.JSON;
import org.pj.metaverse.entity.BaseLogEventRecord;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author pengjie
 * @date 15:01 2022/5/25
 **/
@Service
@Slf4j
public class RedisListener implements MessageListener {
    public static String REDIS_LISTENER_KEY = "meta-verse:event:topic";
     /**
      * 接收到消息后的操作
      * @author pengjie
      * @date 2022/5/25 15:02
      * @param message 信息体
      * @param pattern pattern
      */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String json = new String(message.getBody());
        BaseLogEventRecord baseLogEventRecord = JSON.parseObject(json, BaseLogEventRecord.class);
        log.info("类型【{}】服务【{}】的【{}】用户发送过来日志，日志内容【{}】",
                baseLogEventRecord.getEventEnum().code,
                baseLogEventRecord.getServerName(),
                baseLogEventRecord.getUserId(),
                baseLogEventRecord.getMessage());
        switch (baseLogEventRecord.getEventEnum()){
            case SYSTEM -> systemEventHandle(baseLogEventRecord);
            case USER_LOGIN -> userLoginEventHandle(baseLogEventRecord);
            case OTHER -> otherEventHandle(baseLogEventRecord);
        }
    }
    private void systemEventHandle(BaseLogEventRecord baseLogEventRecord) {

    }

    private void userLoginEventHandle(BaseLogEventRecord baseLogEventRecord) {

    }
    private void otherEventHandle(BaseLogEventRecord baseLogEventRecord) {

    }
}
