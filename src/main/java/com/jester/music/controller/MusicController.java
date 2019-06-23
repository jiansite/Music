package com.jester.music.controller;
import	java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jester.music.mode.Music;
import com.jester.music.utils.HttpUtil;
import com.jester.music.utils.Result;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @className: MusicController
 * @description: 获取音乐信息
 * @author: Jester
 * @email: shujian.jiansite@gmail.com
 * @date: 2019-06-23 14:32
 * @version: version 1.0.0
 */
@RestController
public class MusicController {
    private final static String MUSIC_URL = "https://www.daimadog.com/music/api.php";

    /**
     * 通过歌名获取音乐信息
     * types,count,source,pages,name
     * @return
     */
    @RequestMapping("/app/open/search")
    public Object search(Music music) {
        try {
            Map<String, String> describe = BeanUtils.describe(music);
            describe.remove("id");
            String content = HttpUtil.doPost(MUSIC_URL,describe);
            JSONArray results = JSONArray.parseArray(content);
            return Result.success(results);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    /**
     * 获取音乐详情
     * types,id,source
     * @return
     */
    @RequestMapping("/app/open/detail")
    public Object detail(Music music) {
        try {
            Map<String, String> describe = BeanUtils.describe(music);
            describe.remove("count");
            describe.remove("pages");
            describe.remove("name");
            String content = HttpUtil.doPost(MUSIC_URL,describe);
            JSONObject results = JSONObject.parseObject(content);
            return Result.success(results);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }
}
