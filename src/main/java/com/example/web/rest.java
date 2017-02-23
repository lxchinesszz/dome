package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liuxin on 17/1/13.
 */
@Controller
public class rest {
    @RequestMapping("/test")
    public ModelAndView get(ModelMap map){
        map.put("user","root");
        return new ModelAndView("test",map);
    }
}
