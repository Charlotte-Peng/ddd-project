package org.pj.metaverse.handle;

import org.pj.metaverse.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengjie
 * @date 14:44 2022/8/29
 **/
@Component
public class GameTypeFactory {
    /**
     * 自定义bean名字前缀
     */
    public static final String GAME_TYPE = "gameType";

    /**
     * 注入到容器里面
     */
    @Autowired
    Map<String, GameTypeHandle> typeHandleMap = new ConcurrentHashMap<>(6);

    /**
     * 获取对应状态的执行类
     * @param type 类型
     * @return 具体状态的执行类
     */
    public GameTypeHandle getState(String type) {
        if (typeHandleMap.containsKey(GAME_TYPE + type)) {
            return typeHandleMap.get(GAME_TYPE + type);
        }
        throw new ServerException("没有找到对应的状态");
    }
}
