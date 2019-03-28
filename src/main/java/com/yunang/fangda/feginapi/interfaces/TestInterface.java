package com.yunang.fangda.feginapi.interfaces;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface TestInterface {

    //    @Headers({"Content-Type: application/json","Accept: application/json"})
    @RequestLine("POST /api/query")
    Object getOwner(@Param("time") String time,
                    @Param("sign") String sign);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @RequestLine("POST /login/login")
    Object tst(@Param("account") String account,
               @Param("password") String password);

}
