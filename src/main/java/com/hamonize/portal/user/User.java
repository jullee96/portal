package com.hamonize.portal.user;

import java.security.Timestamp;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.hamonize.portal.paging.PagingVo;
import com.hamonize.portal.util.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tbl_admin_user")
public class User extends PagingVo {
	// 센터 관리자
	@Size(max=50)
	private String domain;

    @Id
	@Size(max=50)
	@Column(name = "user_id")
	private String userid;

	@Column(name = "pass_wd")
    @Size(max=300)
	private String passwd;

	@Column(name = "user_name")
    @Size(max=50)
	private String username;

	private String email;

	private String salt;

	@Comment(value = "활성 비활성 구분 값 > 1 : 활성 , 0 : 비활성")
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

	private String picture;

	private String roles;


}
