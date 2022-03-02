package com.hamonize.portal.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
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

	@NotNull
	@Column(name = "pass_wd")
    @Size(max=300)
	@Comment("유저 패스워드")
	private String passwd;

	
	@NotNull
	@Column(name = "user_name")
    @Size(max=50)
	@Comment("유저 이름")
	private String username;

	@NotNull
	@Size(max=50)
	@Comment("유저 이름")
	private String email;

	@NotNull
	@Comment("비밀번호 암호화 솔트 값")
	private String salt;

	@Transient
	@Comment("활성 비활성 구분 값 > 1 : 활성 , 0 : 비활성")
	private String status;
	
	@Column(name = "rgstr_date")
	private LocalDateTime rgstrDate;

	@Column(name = "updt_date")
	private LocalDateTime updtDate;
	
    @Transient
	@Column(name="login_check",columnDefinition = "integer default 0")
	private int logincheck;

	@Transient
	@Comment("프로필 이미지")
    @Size(max=300)
	private String picture;
	
	@Transient
	@Comment("유저권한 포탈 유저 : USER / 어드민 유저 : ADMIN ")
	private String role;


	/** 회사정보 */
	// @Transient
	@Column(name = "com_nm")
	@Comment("회사명")
	private String comNm;
	
	// @Transient
	@Column(name = "rprs_nm")
	@Comment("대표명")
	private String rprsNm;

	// @Transient
	@Size(max=50)
	@Column(name = "com_no")
	@Comment("사업자 번호")
	private String comNo;
	
}
