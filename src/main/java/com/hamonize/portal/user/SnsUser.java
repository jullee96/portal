package com.hamonize.portal.user;

import lombok.Builder;

public class SnsUser extends User {
	private Long seq;
    private String name;
    private String email;
    private String picture;
    private String role;

    @Builder
    public SnsUser(Long seq, String name, String email, String picture, String role){
        this.seq = seq;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public SnsUser update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role;
    } 

}
