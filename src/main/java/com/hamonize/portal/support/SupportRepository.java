package com.hamonize.portal.support;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, String>{

    Support findBySeq(int seq);
    
}
