package com.project.spring.new_main_project.service.impl;


import com.project.spring.new_main_project.exception.UserNotFoundException;
import com.project.spring.new_main_project.model.User;
import com.project.spring.new_main_project.repository.UserRepository;
import com.project.spring.new_main_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public void updateResetPassword(String token, String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isPresent()){
         user.get().setResetPasswordToken(token);
         userRepository.save(user.get());
        } else{
            throw new UserNotFoundException("There is no user with such email");
        }
    }

    public User get(String resetPassword){
        return userRepository.findByResetPasswordToken(resetPassword);
    }

    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(newPassword);

        user.setPassword(encodePassword);
        user.setResetPasswordToken(null);

        userRepository.save(user);
    }
}









