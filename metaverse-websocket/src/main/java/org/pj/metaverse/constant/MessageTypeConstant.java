package org.pj.metaverse.constant;

/**
 * @author pengjie
 * @date 10:37 2022/8/23
 **/
public interface MessageTypeConstant {
    /**
     * 心跳请求
     */
    String HEART_BEAT = "HEART_BEAT";
    /**
     * 错误消息
     */
    String ERROR = "ERROR";
    /**
     * 进入游戏
     */
    String ENTER_GAME = "ENTER_GAME";
    /**
     * 获取角色信息
     */
    String GET_ROLE_INFO = "GET_ROLE_INFO";
    /**
     * 获取城镇地图信息
     */
    String GET_CITY_MAP_INFO = "GET_CITY_MAP_INFO";
    /**
     * 获取副本地图信息
     */
    String GET_INSTANCE_MAP_INFO = "GET_INSTANCE_MAP_INFO";
    /**
     * 获取剧情信息
     */
    String GET_STORY_INFO = "GET_STORY_INFO";
    /**
     * 地图移动
     */
    String MAP_MOVE = "MAP_MOVE";
    /**
     * 地图事件
     */
    String MAP_EVENT = "MAP_EVENT";
    /**
     * 退出游戏
     */
    String EXIT_GAME = "EXIT_GAME";
}
