package com.jester.music.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jester
 * @version version 1.0.0
 * @className Results
 * @description Result Util
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 12:42
 */
public class Results {
    private static JSONObject jsonObject = new JSONObject();

    public static Object success() {
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        jsonObject.put("data", null);
        return jsonObject;

    }

    public static Object success(String msg) {
        jsonObject.put("code", 200);
        jsonObject.put("msg", msg);
        jsonObject.put("data", null);
        return jsonObject;
    }

    public static Object success(Object data) {
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        jsonObject.put("data", data);
        return jsonObject;

    }

    public static Object success(JSONArray data, int pageNum, int pageSize) {
        if (data==null){
            jsonObject.put("code", 201);
            jsonObject.put("count", 0);
            jsonObject.put("msg", "error");
            jsonObject.put("data", null);
            return jsonObject;
        }
        int total = data.size();
        int offset = pageNum * pageSize;
        int limit = pageNum * pageSize + pageSize;
        List result;
        if (offset > total) {
            result = new ArrayList();
        } else if (limit > total) {
            result = data.subList(offset, total);
        } else {
            result = data.subList(pageNum * pageSize, pageNum * pageSize + pageSize);
        }
        jsonObject.put("code", 200);
        jsonObject.put("count", total);
        jsonObject.put("msg", "success");
        jsonObject.put("data", result);
        return jsonObject;

    }

    public static Object success(String key, Object value) {
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        jsonObject.put(key, value);
        return jsonObject;
    }

    public static Object success(String key, String value) {
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        jsonObject.put(key, value);
        return jsonObject;
    }

    public static Object success(Object data, String msg) {
        jsonObject.put("code", 200);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        return jsonObject;

    }

    public static Object error() {
        jsonObject.put("code", 201);
        jsonObject.put("msg", "error");
        jsonObject.put("data", null);
        return jsonObject;

    }

    public static Object error(String msg) {
        jsonObject.put("code", 201);
        jsonObject.put("msg", msg);
        jsonObject.put("data", null);
        return jsonObject;
    }
}
