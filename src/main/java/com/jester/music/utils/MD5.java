package com.jester.music.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 *
 * @author Jester
 * @email shujian.jiansite@gmail.com
 * @date 2019-06-23 18:00
 * @version version 1.0.0
 */
public class MD5 {
    public final static String get(String str) {

        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");

            byte[] bs = md5.digest(str.getBytes());
            StringBuilder sb = new StringBuilder(40);
            for (byte x : bs) {
                if ((x & 0xff) >> 4 == 0) {
                    sb.append("0").append(Integer.toHexString(x & 0xff));
                } else {
                    sb.append(Integer.toHexString(x & 0xff));
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
