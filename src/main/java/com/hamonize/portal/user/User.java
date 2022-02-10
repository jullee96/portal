package com.hamonize.portal.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.hamonize.portal.paging.PagingVo;

import org.hibernate.annotations.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbl_admin_user")
public class User extends PagingVo {
	// 센터 관리자
	@Size(max=50)
	@Comment("도메인 명")
	private String domain;

    @Id
	@Size(max=50)
	@Column(name = "user_id")
	@Comment("유저 아이디")
	private String userid;

	@Column(name = "pass_wd")
    @Size(max=300)
	@Comment("유저 패스워드")
	private String passwd;

	@Column(name = "user_name")
    @Size(max=50)
	@Comment("유저 이름")
	private String username;

	private String email;

	@Comment("비밀번호 암호화 솔트 값")
	private String salt;

	@Comment("활성 비활성 구분 값 > 1 : 활성 , 0 : 비활성")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rgstr_date")
	private Date rgstrDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updt_date")
	private Date updtDate;
	
    @Column(name="login_check",columnDefinition = "integer default 0")
	private int logincheck;

	@Column(name="org_seq")
	private int[] orgseq;
	
	@Column(name="org_nm")
	private String orgnm;

	@Comment("프로필 이미지")
	private String picture;
	
	@Comment("유저권한 포탈 유저 : USER / 어드민 유저 : ADMIN ")
	private String role;

	@Column(name="authkey")
	@Comment("회원가입시 인증키")
	private String authKey;
	
}
