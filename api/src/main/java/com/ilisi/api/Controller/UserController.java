package com.ilisi.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilisi.api.Dto.UserDTO;
import com.ilisi.api.Service.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@RestController
@CrossOrigin(value="*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.retrieve();
    }

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        // return userService.fromUser(userService.save(userService.toUser(userDTO)));
        return userService.create(userDTO);
    }
    
    @PutMapping("/user/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, int id) {
        return userService.update(userDTO,id);
    }
    
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(name="id") int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable(name="id") int id) {
        return userService.getUser(id);
    }
    
}
