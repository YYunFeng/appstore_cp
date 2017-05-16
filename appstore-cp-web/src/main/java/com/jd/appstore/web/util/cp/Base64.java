package com.jd.appstore.web.util.cp;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: YYF
 * Date: 12-8-21
 * Time: 下午6:42
 * To change this template use File | Settings | File Templates.
 */
public class Base64 {
    /**
     * 编码
     *
     * @param bstr
     * @return String
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     *
     * @param str
     * @return string
     */
    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bt;
    }
}
