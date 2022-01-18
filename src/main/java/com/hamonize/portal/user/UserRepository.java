package com.hamonize.portal.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByUserid(String userid);
    public User findByUsername(String username);
      
}
