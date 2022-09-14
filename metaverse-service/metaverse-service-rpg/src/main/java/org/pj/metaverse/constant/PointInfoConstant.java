package org.pj.metaverse.constant;

/**
 * @author pengjie
 * @date 9:59 2022/9/13
 **/
public interface PointInfoConstant {
    interface Type {
        /**
         * 问答
         */
        Integer QA = 1;
        /**
         * 商店
         */
        Integer SHOP = 2;
        /**
         * 小怪
         */
        Integer MONSTER = 3;
        /**
         * 奖励buff
         */
        Integer BUFF = 4;
        /**
         * 奖励当局货币
         */
        Integer CURRENCY = 5;
        /**
         * 消除debuff
         */
        Integer DEBUFF = 6;
        /**
         * 消除危险值
         */
        Integer DANGER = 7;
        /**
         * BOSS
         */
        Integer BOSS = 8;
        /**
         * 剧情
         */
        Integer STORY = 9;
    }
    Integer[] TYPES = {Type.QA, Type.SHOP, Type.MONSTER, Type.BUFF, Type.CURRENCY, Type.DEBUFF, Type.DANGER, Type.BOSS, Type.STORY};
}
