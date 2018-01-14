package com.deyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ade on 1/13/18.
 */

@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(){
        return "index";
    }
}
