package com.hamonize.portal.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.userdetails.User;


@Getter
@Setter
public class SecurityUser extends User{

    private String userid;
    private String passwd;
    private String status;
    private String username;

    // 결제정보
    private String domain;
    private int itemno;
    
   
    public SecurityUser(String userid, String passwd, String domain) {
        super(userid, passwd, makeGrantedAuthority());
        this.userid = userid;
        this.passwd = passwd;
        this.domain = domain;
    }

    private static Set<GrantedAuthority> makeGrantedAuthority() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE.USER.getValue()));

        return grantedAuthorities;
    }
}