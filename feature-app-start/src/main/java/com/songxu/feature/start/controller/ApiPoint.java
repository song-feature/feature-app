package com.songxu.feature.start.controller;

import com.songxu.feature.api.model.Result;
import com.songxu.feature.start.aop.ApiLogMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author songxu
 * @version 1.0.0
 * @date 2021-04-11 10:58:04
 */
@Controller
@RequestMapping("/api")
public class ApiPoint {

    @ApiLogMethod(logRetValue = true)
    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> entry(String input) {
        return Result.ok("hello:" + input);
    }


}
