package com.example.appKp6.repo;


import com.example.appKp6.entity.Role;
import com.example.appKp6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
    Optional<User> findOneByEmailAndPassword(String email, String password);

    User findByUsername(String username);

    List<User> findByStatus(String status);

}
