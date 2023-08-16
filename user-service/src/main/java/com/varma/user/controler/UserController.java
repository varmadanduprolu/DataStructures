package com.varma.user.controler;


import com.varma.user.entity.User;
import com.varma.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("new")
    public void saveUser(User user){
        log.info("inside saveUser method userService");
        userService.saveUser(user);
    }

    @GetMapping("id/{userId}")
    public User fetchTheUser(@PathVariable Long userId){
        log.info("inside teh fetchTheUser Method userController");
        return userService.fetchTheUser(userId);
    }

}
