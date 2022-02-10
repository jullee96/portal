package com.hamonize.portal.signup;

import com.hamonize.portal.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignupRepository extends JpaRepository<User, String>{
    List<User> findByUserid(String userid);
    List<User> findByEmail(String email);
    boolean existsByUserid(String userid);
    
}
