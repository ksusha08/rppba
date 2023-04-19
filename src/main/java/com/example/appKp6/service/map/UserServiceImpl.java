package com.example.appKp6.service.map;


import com.example.appKp6.dto.LoginDTO;
import com.example.appKp6.entity.Supplier;
import com.example.appKp6.entity.User;
import com.example.appKp6.entity.Role;
import com.example.appKp6.exception.SupplierNotFoundException;
import com.example.appKp6.exception.UserNotFoundException;
import com.example.appKp6.repo.UserRepo;
import com.example.appKp6.service.SupplierService;
import com.example.appKp6.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User loginUser(LoginDTO loginDTO){

        User user = null;
        try{
            user = userRepo.findByUsername(loginDTO.getUsername());
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
        String userName = user.getUsername();
        if(userRepo.findByUsername(userName) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user exist!");
        }


        if (user.getUsername() == null || user.getUsername().isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }


        return userRepo.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public User findById(Long aLong) {
        return userRepo.findById(aLong).orElseThrow(()->new UserNotFoundException(aLong));
    }

    @Override
    public User save(User object) {
        return userRepo.save(object);
    }

    @Override
    public void delete(User object) {
        userRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepo.deleteById(aLong);
    }


    public User update(User newUser, Long id){
        return userRepo.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());

            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setPatronymic(newUser.getPatronymic());

            return userRepo.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));
    }
}
