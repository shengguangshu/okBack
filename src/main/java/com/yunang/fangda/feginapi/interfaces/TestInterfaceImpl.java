package com.yunang.fangda.feginapi.interfaces;

import com.yunang.fangda.feginapi.utils.FeginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class TestInterfaceImpl {

    @Autowired
    private TestInterface testInterface = new FeginUtils().object.target(TestInterface.class, FeginUtils.URL);

    public void test() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String stuno ="06038";
        String time = "20170327152505";//simpleDateFormat.format(System.currentTimeMillis());
        String stuname = "小高";
//        String sign = "9C1FE04B8F6F268A92CD5FFE8B7E7A35";
//        String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
        String str = stuname+"|"+stuno+"|"+time+"|"+"123456";
        String s = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
//        String s1 = s.toUpperCase();
        String s2 = DigestUtils.md5DigestAsHex(s.getBytes(StandardCharsets.UTF_8));
//        String sign = s2.toUpperCase();

        System.out.println(s2);
        Object object = testInterface.getOwner(time,s2,stuno,stuname);
        System.out.println(object);
    }

    public void test2(){
        Object weihuzu = testInterface.tst("weihuzu", "123456");
        System.out.println(weihuzu.toString());
    }

    public static void main(String[] args) {
        String stuno ="06038";
        String time = "20170327152505";//simpleDateFormat.format(System.currentTimeMillis());
        String stuname = "小高";
        String str = stuname+"|"+stuno+"|"+time+"|"+"123456";
        String s = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
        String s1 = s.toUpperCase();
        String s2 = DigestUtils.md5DigestAsHex(s1.getBytes(StandardCharsets.UTF_8));
        String sign = s2.toUpperCase();
        String sign2 = "sign="+sign;
        URL url = null;
        HttpURLConnection httpurlconnection = null;
        try {
            url = new URL("http://192.168.1.12/api/query/QUERY.asp");
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);

            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            String s4 = "time="+time+"&"+"stuname="+stuname+"&"+"stuno="+stuno+"&"+sign2;
            System.out.println(s4);
            httpurlconnection.getOutputStream().write(s4.getBytes());

            httpurlconnection.getOutputStream().flush();
            httpurlconnection.getOutputStream().close();
            int code = httpurlconnection.getResponseCode();
            System.out.println("code    " + code);

            if (code == 200) {

                String cookie = httpurlconnection.getHeaderField("Set-Cookie ");
                System.out.println(cookie);
                // httpurlconnection.setRequestProperty( "Cookie", cookie);

                DataInputStream in = new DataInputStream(httpurlconnection
                        .getInputStream());
                int len = in.available();
                byte[] by = new byte[len];
                in.readFully(by);
                String rev = new String(by);
                System.out.println(rev);
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
    }
}
