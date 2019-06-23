package com.jester.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @className: IndexController
 * @description: 首页跳转
 * @author: Jester
 * @email: shujian.jiansite@gmail.com
 * @date: 2019-06-23 18:00
 * @version: version 1.0.0
 */
@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(ModelAndView modelAndView) {

        return "index";
    }
}
