package com.jester.music.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.jester.music.mode.Music;
import com.jester.music.utils.HttpUtil;
import com.jester.music.utils.Result;
import com.jester.music.utils.Results;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Jester
 * @version version 1.0.0
 * @className MusicController
 * @description 获取音乐信息
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 14:32
 */
@RestController
public class MusicController {
    private final static String MUSIC_URL = "https://www.daimadog.com/music/api.php";
    private final static int V1_ID = 26;
    private final static String V1_COUNT = "https://v1.itooi.cn/tencent/topList?id=%d&pageSize=9999&page=0&format=1";
    private final static String V1_MUSIC = "https://v1.itooi.cn/tencent/topList?id=%d&pageSize=%d&page=0&format=1";
    private final static String SEARCH_MUSIC = "https://v1.itooi.cn/%s/search?keyword=%s&type=song&pageSize=20&page=0&format=1";

    /**
     * 获取排行榜音乐
     *
     * @return
     */
    @RequestMapping("/app/open/topList")
    public Object topList(/*@RequestParam(name = "pageNum", defaultValue = "0") */Integer pageNum,/*@RequestParam(name = "pageSize", defaultValue = "30") */Integer pageSize) {
        System.out.println("pageNum = [" + pageNum + "], pageSize = [" + pageSize + "]");
        JSONArray data = HttpUtil.sendGet(String.format(V1_COUNT, V1_ID)).getJSONArray("data");
        return Results.success(data,pageNum,pageSize);

    }

    /**
     * 获取排行榜音乐
     *
     * @return
     */
    @RequestMapping("/app/open/search")
    public Object search(String name,String source,Integer pageNum,Integer pageSize) {
        System.out.println("pageNum = [" + pageNum + "], pageSize = [" + pageSize + "]");
        JSONArray data = HttpUtil.sendGet(String.format(SEARCH_MUSIC,source, name)).getJSONArray("data");
        return Results.success(data,pageNum,pageSize);

    }
}
