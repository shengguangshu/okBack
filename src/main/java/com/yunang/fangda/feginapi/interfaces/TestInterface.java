package com.yunang.fangda.feginapi.interfaces;

import feign.Body;
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
    String getOwner(@Param("time") String time,
                    @Param("sign") String sign,
                    @Param("stuno") String stuno,
                    @Param("stuname") String stuname);

}
