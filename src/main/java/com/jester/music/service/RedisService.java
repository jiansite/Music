package com.jester.music.service;

import java.util.List;

/**
 * RedisService
 *
 * @author Jester
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 12:42
 * @version version 1.0.0
 */
public interface RedisService {
    /**
     * 从左边插入
     *
     * @param key
     * @param value
     * @return
     */
    Long leftPush(String key, Object value);

    /**
     * 从右边插入
     *
     * @param key
     * @param value
     * @return
     */
    Long rightPush(String key, Object value);

    /**
     * 从左边多值插入
     *
     * @param key
     * @param value
     * @return
     */
    Long leftPushAll(String key, Object... value);

    /**
     * 从右边多值插入
     *
     * @param key
     * @param value
     * @return
     */
    Long rightPushAll(String key, Object... value);

    /**
     * 弹出最右边的元素
     *
     * @param key
     * @return
     */
    Object rightPop(String key);

    /**
     * 弹出最左边的元素
     *
     * @param key
     * @return
     */
    Object leftPop(String key);

    /**
     * 获取所有的元素
     *
     * @param key
     * @return
     */
    List getAll(String key);

    /**
     * 获取数量
     *
     * @param key
     * @return
     */
    Long size(String key);

    /**
     * 通过key获取值
     *
     * @param key
     * @return
     */
    Object get(final String key);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean hasKey(final String key);

    /**
     * 更新生命周期
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    boolean updateOutTime(String key, Object value, long time);

    void set(String key, String valueOf, long timeOut);
}
