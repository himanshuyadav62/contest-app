package com.contest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contest.api.entity.User;

public interface UserRepo extends JpaRepository<User,String> {
    
    @Query("SELECT u FROM User u WHERE u.email = :username OR u.username = :username")
    Optional<User> findByEmailOrUserName(String username);
    
}
