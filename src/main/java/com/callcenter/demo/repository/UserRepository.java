package com.callcenter.demo.repository;

import com.callcenter.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Integer userId);

    int deleteUserByEmail(String email);

    int deleteUserById(Integer userId);


}

