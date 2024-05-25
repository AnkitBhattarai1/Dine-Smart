package com.example.user_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.Services.UserService;
import com.example.user_service.dto.NonRegisteredRequestUser;
import com.example.user_service.dto.RequestUser;
import com.example.user_service.dto.ResponseUser;
import com.example.user_service.dto.UpdatingUserRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {
    /*
     * /user/all--get all users.
     * /user/ @post--save a single user(accept a user request object)
     * /user/{id} gets a user object of the id
     * /user/email gets the user object of the given email address
     */

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<ResponseUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseUser saveUser(@RequestBody RequestUser user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseUser getUserById(@PathVariable(name = "id") String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/email")
    public ResponseUser getUserByEmail(@RequestParam(name = "email") String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public ResponseUser updateUserDetails(@PathVariable String id, @RequestBody UpdatingUserRequest user) {
        return userService.updateUser(user, id);
    }

    @PostMapping("/unregistered")
    public ResponseUser saveNonRegisteredUser(@RequestBody NonRegisteredRequestUser user) {
        // TODO: process POST request
        return userService.savenonRegisteredUser(user);
    }
}
