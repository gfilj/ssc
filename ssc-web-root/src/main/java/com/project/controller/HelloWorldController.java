package com.project.controller;

import com.project.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by goforit on 2017/7/21.
 */

@Controller
@RequestMapping("/")
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Greeting sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping(method = RequestMethod.GET,value = "/socket.io/")
    public @ResponseBody String polling(@RequestParam(value = "EIO", required = false) String EIO,
    		@RequestParam(value = "transport", required = false) String transport,
    		@RequestParam(value = "t", required = false) String t) {
        return "ok";
    }

}
