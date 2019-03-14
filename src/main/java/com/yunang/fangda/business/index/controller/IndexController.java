package com.yunang.fangda.business.index.controller;

import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@RestController
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/v2/api-docs");
    }
}
