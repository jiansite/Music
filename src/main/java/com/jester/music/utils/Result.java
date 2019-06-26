package com.jester.music.utils;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.alibaba.fastjson.JSONObject;


/**
 * @author Jester
 * @version version 1.0.0
 * @className Result
 * @description Result Util
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 12:42
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private final static String DEFAULT_KEY = "result";
    private int code;
    private long count;
    private String msg;
    private JSONObject result = new JSONObject();


    private Result(int code, long count, String msg) {
        this.code = code;
        this.count = count;
        this.msg = msg;
    }

    private Result(int code, long count, String msg, Object value) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.result.put(DEFAULT_KEY, value);
    }

    private Result(int code, long count, String msg, JSONArray value) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.result.put(DEFAULT_KEY, value);
    }

    private Result(int code, long count, String msg, String key, Object value) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.result.put(key, value);
    }

    public static Result success() {
        return new Result(Status.SUCCESS.code, 0, Status.SUCCESS.msg);
    }

    public static Result success(Integer code) {
        return new Result(code, 0, Status.SUCCESS.msg);
    }

    public static Result success(String msg) {
        return new Result(Status.SUCCESS.code, 0, msg);
    }

    public static Result success(JSONObject result) {
        return new Result(Status.SUCCESS.code, result.size(), Status.SUCCESS.msg, result);
    }

    public static Result success(long count, JSONObject result) {
        return new Result(Status.SUCCESS.code, count, Status.SUCCESS.msg, result);
    }

    public static Result success(Object result) {
        return new Result(Status.SUCCESS.code, 0, Status.SUCCESS.msg, result);
    }

    public static Result success(JSONArray result) {
        return new Result(Status.SUCCESS.code, result.size(), Status.SUCCESS.msg, result);
    }

    public static Result success(String key, Object result) {
        return new Result(Status.SUCCESS.code, 0, Status.SUCCESS.msg, key, result);
    }

    public static Result error() {
        return new Result(Status.ERROR.code, 0, Status.ERROR.msg);
    }

    public static Result error(Integer code) {
        return new Result(code, 0, Status.ERROR.msg);
    }

    public static Result error(String msg) {
        return new Result(Status.ERROR.code, 0, msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, 0, msg);
    }

    public enum Status {
        /**
         * Successful
         */
        SUCCESS(200, "success"),
        /**
         * Unsuccessful
         */
        ERROR(201, "error");

        private int code;
        private String msg;

        Status(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}