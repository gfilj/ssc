package com.project.webdriver.controller;

import com.project.webdriver.model.User;
import com.project.webdriver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by goforit on 2017/9/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public @ResponseBody
    Collection<User> add(@RequestBody User user) {
        userService.add(user);
        return userService.list();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/del")
    public @ResponseBody
    Collection<User> del(@RequestBody User user) {
        userService.del(user);
        return userService.list();
    }

}
