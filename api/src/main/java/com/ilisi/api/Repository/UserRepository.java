package com.ilisi.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ilisi.api.Bo.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
}