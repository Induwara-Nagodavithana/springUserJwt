package com.callcenter.demo.service;


import com.callcenter.demo.model.AuthenticationRequest;
import com.callcenter.demo.model.CommonResponse;
import com.callcenter.demo.model.User;
import com.callcenter.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public User createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User userToUpload) {
        userToUpload.setId(id);
        System.out.println(userToUpload);
        return userRepository.save(userToUpload);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByUserId(Integer userId) {
        return userRepository.findUserById(userId);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteUserById(id);
    }

}
