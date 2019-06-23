package com.jester.music.enums;

/**
 * @author Jester
 */
public enum EnableEnum {
    /** 删除 */
    OFF(0),
    /** 启用 */
    ON(1);
    Integer num;

    EnableEnum(Integer num) {
        this.num = num;
    }

    public Integer getVal() {
        return this.num.intValue();

    }
}
