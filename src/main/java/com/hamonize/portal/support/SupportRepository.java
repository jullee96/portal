package com.hamonize.portal.support;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SupportRepository extends JpaRepository<Support, String>{

    Support findBySeq(Long long1);

    @Modifying
    @Query(
        value = "UPDATE tbl_support SET type = :#{#vo.type} , title = :#{#vo.title} , updt_date = :#{#vo.updtdate}, contents = :#{#vo.contents}, img_seqs = :#{#vo.imgseqs}  WHERE seq = :#{#vo.seq} " , nativeQuery = true
    )
    int update(@Param("vo") Support vo);

    @Query(
        value = "SELECT * FROM tbl_support WHERE user_id = :userid " , nativeQuery = true
    )
    List<Support> findByUserid(@Param("userid") String userid);

    Page<Support> findAllByUserid(org.springframework.data.domain.Pageable pageable, String userid);

    Page<Support> findAllByUseridAndRgstrdateBetween(org.springframework.data.domain.Pageable pageable, String userid ,LocalDateTime startDate, LocalDateTime endDate);

    Long countByUseridAndRgstrdateBetween(String userid, LocalDateTime startDate, LocalDateTime endDate);

    Page<Support> findAllByUseridAndRgstrdateLessThanEqual(org.springframework.data.domain.Pageable pageable, String userid, LocalDateTime endDate);

    Page<Support> findBySeq(org.springframework.data.domain.Pageable pageable, Long tmpLong);

    Long countBySeq(Long tmpLong);

    Page<Support> findAllByUseridAndTitleContainingIgnoreCaseAndRgstrdateBetween(org.springframework.data.domain.Pageable pageable, String keyword,
            String userid, LocalDateTime startDate, LocalDateTime endDate);

    Page<Support> findByUseridAndTitleContainingIgnoreCase(org.springframework.data.domain.Pageable pageable, String keyword, String keyword2);

    Long countByUseridAndTitleContainingIgnoreCaseAndRgstrdateBetween(String userid, String keyword,
                LocalDateTime startDate, LocalDateTime endDate);

    Long countByUseridAndTitleContainingIgnoreCase(String userid, String keyword);

    Long countByUserid(String userid);

}
