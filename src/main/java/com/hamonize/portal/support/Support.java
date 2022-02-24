package com.hamonize.portal.support;

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
    @Size(max=50)
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE, 
            generator="TBL_SUPPORT_SEQ_GEN"     
            )
    @Comment("문의글 시퀀스 번호")
    private int seq;

    @Comment("구분")
    private String type;

    @Comment("도메인 정보")
    private String domain;

    @Comment("유저 아이디")
    private String userid;

    @Comment("담당자 이름")
    private String name;

    @Comment("연락처")
    private String tel;

    @Comment("이메일")
    private String email;

    @Comment("제목")
    private String title;

    @Comment("내용")
    private String contents;

    @Comment("내용")
    private Integer[] imgseq;
}
