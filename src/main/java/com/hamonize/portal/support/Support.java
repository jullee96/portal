package com.hamonize.portal.support;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(
            name="TBL_SUPPORT_SEQ_GEN",
            sequenceName="TBL_SUPPORT_SEQ",
            initialValue=1, 
            allocationSize=1                                
            )
@Table(name="tbl_support")
public class Support {
    
    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE, 
            generator="TBL_SUPPORT_SEQ_GEN"     
            )
    @Comment("문의글 시퀀스 번호")
    private int seq;

    @Comment("구분 > 1:결제, 2: 기술 , 3: 기타 ")
    private Integer type;

    @Comment("유저 아이디")
    @Column(name = "user_id")
    private String userid;

    @Comment("이메일")
    private String email;

    @Comment("제목")
    private String title;

    @Comment("내용")
    @Column(columnDefinition="TEXT")
    private String contents;

    @Comment("진행 상태 > 0: 처리중, 1:완료")
    private Integer status;

    @Column(name = "ins_date")
    private LocalDateTime insdate;

}
