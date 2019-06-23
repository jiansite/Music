package com.jester.music.mode.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: UserSession
 * @description: UserSession Util
 * @author: Jester
 * @email: shujian.jiansite@gmail.com
 * @date: 2019-06-23 12:42
 * @version: version 1.0.0
 */
public class UserSession {
    public static final String USER_ID = "userId";
    private static Logger logger = LoggerFactory.getLogger(UserSession.class);

    protected final static ThreadLocal<Map<String, Object>> threadContext = new MapThreadLocal();

    private UserSession() {
    }

    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    public static Object remove(String key) {
        return getContextMap().remove(key);
    }

    public static Object get(String key) {
        return getContextMap().get(key);
    }

    public static Integer getUserId() {
        Map<String, Object> contextMap = getContextMap();
        if (contextMap == null || !contextMap.containsKey(USER_ID)) {
            return -1;
        }
        return (Integer) getContextMap().get(USER_ID);
    }

    public static void setUserId(Integer userId) {
        put(USER_ID, userId);
    }

    public static void set(String key, Object value) {
        put(key, value);
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>() {
                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value) {
                    if (logger.isDebugEnabled()) {
                        if (containsKey(key)) {
                            logger.debug(String.format("Overwritten attribute to thread context: %s = %s", key, value));
                        } else {
                            logger.debug(String.format("Added attribute to thread context: %s = %s", key, value));
                        }
                    }

                    return super.put(key, value);
                }
            };
        }
    }

    /**
     *
     * @return Map<String, Object>
     */
    protected static Map<String, Object> getContextMap() {
        return threadContext.get();
    }

    public static void clear() {
        getContextMap().clear();
    }

    public static boolean isNull() {
        return getContextMap().size() == 0;
    }
}