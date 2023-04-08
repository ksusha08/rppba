package com.example.appKp6.service;


import com.example.appKp6.dto.LoginDTO;
import com.example.appKp6.entity.User;
import com.example.appKp6.entity.Role;
import com.example.appKp6.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loginUser(LoginDTO loginDTO){

        User user = null;
        try{
            user = userRepo.findByName(loginDTO.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }

        String pass = loginDTO.getPassword();
        if(user != null && passwordEncoder.matches(pass, user.getPassword())){
            return user;
        }
        return null;
    }

    public User createEmployee(User user){
        String userName = user.getName();
        if(userRepo.findByName(userName) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user exist!");
        }

        user.getRoles().add(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employeename is required");
        }

        return userRepo.save(user);
    }

}
