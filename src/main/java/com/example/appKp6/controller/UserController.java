package com.example.appKp6.controller;

import com.example.appKp6.dto.LoginDTO;
import com.example.appKp6.entity.User;
import com.example.appKp6.exception.UserNotFoundException;
import com.example.appKp6.repo.UserRepo;
import com.example.appKp6.service.map.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userServiceImpl.createEmployee(newUser);
    }

    @PostMapping("/loginuser")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO){

        User user = userServiceImpl.loginUser(loginDTO);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userServiceImpl.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userServiceImpl.findById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id ){

        return userServiceImpl.update(newUser,id);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        userServiceImpl.deleteById(id);
        return  "User with id "+id+" has been deleted success";
    }

}
