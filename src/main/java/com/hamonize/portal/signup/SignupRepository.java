package com.hamonize.portal.signup;

import java.util.List;

import javax.transaction.Transactional;

import com.hamonize.portal.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SignupRepository extends JpaRepository<User, String>{
    List<User> findByUserid(String userid);
    List<User> findByEmail(String email);
    boolean existsByUserid(String userid);
    
    @Modifying
    @Query(
        value = "UPDATE tbl_admin_user SET authkey = :authkey, role = :role WHERE email = :email ", nativeQuery = true
    )
    int updateAuthkey(@Param("email") String email, @Param("authkey") String authkey, @Param("role") String role);
    
    @Modifying
    @Query(
        value = "UPDATE tbl_admin_user SET status = :#{#vo.status}, updt_date = :#{#vo.updtdate} WHERE user_id = :#{#vo.userid} ", nativeQuery = true
    )
    int updateStatus(@Param("vo") User vo);
    
}
