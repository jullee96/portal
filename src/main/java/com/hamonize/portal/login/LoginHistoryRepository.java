package com.hamonize.portal.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, String> {
    @Modifying
    @Query(
        value = "UPDATE tbl_admin_login_history SET logout_date = :#{#vo.logoutdate} , time_spent = :#{#vo.timespent} WHERE seq = :#{#vo.seq} " , nativeQuery = true
    )
    public void update(@Param("vo") LoginHistory vo);

}
