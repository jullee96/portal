package com.hamonize.portal.subscribe;

import java.util.List;
import java.util.Optional;

import com.hamonize.portal.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepostory extends JpaRepository<Subscribe, String>{
    List<Subscribe> findAllByUserid(String userid);

}
