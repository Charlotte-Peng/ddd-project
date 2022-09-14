package org.pj.metaverse.constant.redis;

/**
 * @author pengjie
 * @date 10:48 2022/8/23
 **/
public interface WebSocketRedisConstant {

    /**
     * websocket的命名空间
     */
    String WEBSOCKET_NAME_SPACE = "websocket:";
    /**
     * websocket自增key
     */
    String WEBSOCKET_AUTO_INCREMENT_KEY = WEBSOCKET_NAME_SPACE+"websocket_auto_increment_key";
    /**
     * websocket的ip地址及绑定的唯一id
     */
    String WEBSOCKET_IP_ADDRESS_BIND_ID = WEBSOCKET_NAME_SPACE+"websocket_ip_address_bind_id";
    /**
     * websocket的类型的信息
     */
    String WEBSOCKET_RPG_TYPE_KEY = WEBSOCKET_NAME_SPACE+"rpg_ip_address_bind_id";
    /**
     * websocketRpg游戏的命名空间
     */
    String WEBSOCKET_RPG_NAME_SPACE = WEBSOCKET_NAME_SPACE+"rpg:";
    /**
     * websocket任务周期时间的key
     */
    String WEBSOCKET_TASK_CYCLE_TIME_KEY = WEBSOCKET_NAME_SPACE+"task_cycle_time_key";
    /**
     * websocket端口号的key
     */
    String WEBSOCKET_PORT_KEY = WEBSOCKET_NAME_SPACE+"port_key";

    interface Type {
        /**
         * rpg游戏的类型
         */
        String RPG = "RPG";
    }
    interface Rpg{
        /**
         * 用户所在地图key
         */
        String USER_MAP_KEY = WEBSOCKET_NAME_SPACE + Type.RPG + ":USER_MAP_KEY:%s";
    }
}
