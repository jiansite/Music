package com.jester.music.controller;

import com.alibaba.fastjson.JSONArray;
import com.jester.music.config.ConfigConst;
import com.jester.music.service.RedisService;
import com.jester.music.utils.HttpUtil;
import com.jester.music.utils.MD5;
import com.jester.music.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取音乐信息
 *
 * @author Jester
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 14:32
 * @version version 1.0.0
 */
@RestController
public class MusicController {
    private final static String MUSIC_URL = "https://www.daimadog.com/music/api.php";
    private final static int V1_ID = 26;
    private final static String V1_COUNT = "https://v1.itooi.cn/tencent/topList?id=%d&pageSize=9999&page=0&format=1";
    private final static String V1_MUSIC = "https://v1.itooi.cn/tencent/topList?id=%d&pageSize=%d&page=%s&format=1";
    private final static String SEARCH_MUSIC = "https://v1.itooi.cn/%s/search?keyword=%s&type=song&pageSize=100&page=0&format=1";

    @Autowired
    RedisService redisService;

    /**
     * 获取排行榜音乐
     *
     * @return
     */
    @RequestMapping("/app/open/topList")
    public Object topList(@RequestParam(name = "page", defaultValue = "0") Integer pageNum, @RequestParam(name = "limit", defaultValue = "30") Integer pageSize) {
        String url = String.format(V1_COUNT, V1_ID);
        String key = MD5.get(url);
        Boolean hasKey = redisService.hasKey(key);
        JSONArray data;
        if (hasKey) {
            String count = (String) redisService.get(key);
            data = HttpUtil.sendGet(String.format(V1_MUSIC, V1_ID, pageSize, pageNum)).getJSONArray("data");
            return Results.success(data == null ? 0 : Integer.valueOf(count), data);
        }
        data = HttpUtil.sendGet(url).getJSONArray("data");
        if (data != null) {
            redisService.set(key, String.valueOf(data.size()), ConfigConst.TIME_OUT);
        }
        return Results.success(data, pageNum, pageSize);

    }

    /**
     * 获取排行榜音乐
     *
     * @return
     */
    @RequestMapping("/app/open/search")
    public Object search(String name, String source, @RequestParam(name = "page", defaultValue = "0") Integer pageNum, @RequestParam(name = "limit", defaultValue = "30") Integer pageSize) {
        JSONArray data = HttpUtil.sendGet(String.format(SEARCH_MUSIC, source, name)).getJSONArray("data");
        return Results.success(data, pageNum, pageSize);

    }
}
