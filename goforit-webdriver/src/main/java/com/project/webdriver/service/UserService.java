package com.project.webdriver.service;

import com.project.webdriver.model.User;
import com.project.webdriver.util.ObjectToFileUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by goforit on 2017/9/5.
 */
@Service
public class UserService {

    public UserService() {
        users = ObjectToFileUtils.readObject();
    }

    private Map<String,User> users = new HashMap<>();

    public void add(User user) {
       if(users.containsKey(user.getUsername())){
           users.get(user.getUsername()).plusDay(user.getDays());
       }else{
           users.put(user.getUsername(),user);
       }
       ObjectToFileUtils.writeObject(users);
    }

    public void del(User user) {
        if(users.containsKey(user.getUsername())){
            users.remove(user.getUsername());
        }
        ObjectToFileUtils.writeObject(users);
    }

    public Collection<User> list() {
        return users.values();
    }

    public boolean isValid(String username){
        if(!users.containsKey(username)){
            return false;
        }else{
            User user = users.get(username);
            if(user.getValideDate().before(new Date())){
                return false;
            }
        }
        return true;
    }

}
