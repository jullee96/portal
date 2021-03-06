package com.hamonize.portal.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BoardConfigRepository extends JpaRepository<BoardConfig,String>{

    BoardConfig getByBcseq(Long bcseq);

    @Modifying
    @Query(
        value = "UPDATE tbl_board_config SET bc_id = :#{#vo.bcid}, bc_used = :#{#vo.bcused}, bc_role = :#{#vo.bcrole}, bc_type = :#{#vo.bctype} , bc_name = :#{#vo.bcname}, updt_date = :#{#vo.updtdate} WHERE bc_seq = :#{#vo.bcseq} " , nativeQuery = true
    )
	void update(@Param("vo") BoardConfig vo);

    List<BoardConfig> findAllByBcused(Sort sort, int i);

  
    BoardConfig findByBcid(String string);
    
}
