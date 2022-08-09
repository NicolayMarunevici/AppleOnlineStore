package com.project.spring.new_main_project.repository;


import com.project.spring.new_main_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    User findByResetPasswordToken(String token);
}
