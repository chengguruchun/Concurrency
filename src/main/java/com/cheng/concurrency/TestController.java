package com.cheng.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author :cheng
 * @Description: postman 测试接口
 * @Date: created in 15:57 2018/6/22
 * @Reference:
 */
@Controller
@Slf4j
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }
}
