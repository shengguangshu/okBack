package com.yunang.fangda.feginapi.interfaces;

import org.springframework.util.DigestUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class TestInterfaceImpl {

    public static void main(String[] args) {
        String stuno = "06038";
        String time = "20170327152505";
        String stuname = "小高";
        String str = stuname + "|" + stuno + "|" + time + "|" + "123456";
        String str2 = "stuname=" + stuname + "&stuno=" + stuno + "&time=" + time + "&";
        try {
            String utils = utils(str2, str, "http://192.168.1.12/api/query/QUERY.asp");
            System.out.println(utils);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param str  参数串(不包含加密) "time=" + time + "&" + "stuname=" + stuname + "&" + "stuno=" + stuno + "&"
     * @param sign 需要加密的串明文 stuname + "|" + stuno + "|" + time + "|" + "123456"
     * @param url1 请求地址 http://192.168.1.12/api/query/QUERY.asp
     * @return
     * @throws IOException
     */
    public static String utils(String str, String sign, String url1) throws IOException {
        URL url = null;
        HttpURLConnection httpurlconnection = null;
        DataInputStream in = null;
        try {
            url = new URL(url1);
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);

            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            String sign1 = sign(sign);
            String s = str + sign1;
            httpurlconnection.getOutputStream().write(s.getBytes());

            httpurlconnection.getOutputStream().flush();
            httpurlconnection.getOutputStream().close();
            int code = httpurlconnection.getResponseCode();

            if (code == 200) {
                String cookie = httpurlconnection.getHeaderField("Set-Cookie ");
                // httpurlconnection.setRequestProperty( "Cookie", cookie);

                in = new DataInputStream(httpurlconnection
                        .getInputStream());
                int len = in.available();
                byte[] by = new byte[len];
                in.readFully(by);
                return new String(by);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                in.close();
            }
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
    }

    /**
     * 加密工具类
     *
     * @param str
     * @return
     */
    private static String sign(String str) {
        String s = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        String s1 = s.toUpperCase();
        String s2 = DigestUtils.md5DigestAsHex(s1.getBytes(StandardCharsets.UTF_8));
        String sign = s2.toUpperCase();
        return "sign=" + sign;
    }
}
