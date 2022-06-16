package org.pj.metaverse.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 对象非空判断工具类
 *
 * @author yangCheng
 */
public class NvlUtils {

    public static boolean isNull(Object[] objs) {
        return objs == null || objs.length == 0;
    }

    public static boolean isNull(Object obj) {
        return obj == null || isNull(obj.toString());
    }

    public static boolean isNull(Integer integer) {
        return integer == null;
    }

    public static boolean isNull(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNull(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNull(String str) {
        return str == null || "".equals(str.trim())
                || "null".equalsIgnoreCase(str);
    }

    public static boolean isNull(Long longs) {
        return longs == null || longs == 0;
    }

    public static boolean isNotNull(Long longs) {
        return !isNull(longs);
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static boolean isNotNull(Collection collection) {
        return !isNull(collection);
    }

    public static boolean isNotNull(Map map) {
        return !isNull(map);
    }

    public static boolean isNotNull(Integer integer) {
        return !isNull(integer);
    }

    public static boolean isNotNull(Object[] objs) {
        return !isNull(objs);
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

}