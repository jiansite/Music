package com.jester.music.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className: Music
 * @description: Music Entity
 * @author: Jester
 * @email: shujian.jiansite@gmail.com
 * @date: 2019-06-23 16:17
 * @version: version 1.0.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private String id;
    private String types;
    private Integer count;
    private String source;
    private Integer pages;
    private String name;
}
