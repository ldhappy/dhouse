package com.ld.dhouse.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/error")
public class ErrorController {



    @RequestMapping("/404")
    public String error404() {
        return "error/404";
    }
    @RequestMapping("/500")
    public String error500() {
        return "error/500";
    }
}
