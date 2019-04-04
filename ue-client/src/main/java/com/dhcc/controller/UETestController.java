package com.dhcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UETestController {

    @RequestMapping("/uetest")
    public String helloworld() {
        return "uetest";
    }

}
