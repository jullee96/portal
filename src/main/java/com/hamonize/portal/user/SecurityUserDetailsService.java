package com.hamonize.portal.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository ur;

    @Override
    public SecurityUser loadUserByUsername(String userid) throws UsernameNotFoundException {

        userid="admin";
        logger.info("userid  >>> {}", userid);
        Optional<User> user = ur.findByUserid(userid);

        logger.info("findByUserid  >>> {}",user.get().getUsername());
        logger.info("findByUserid  >>> {}",user.get().getEmail());
        logger.info("findByUserid  >>> {}",user.get().getPasswd());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        if (userid.equals("admin")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE.ADMIN.getValue()));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority(ROLE.USER.getValue()));
        }

        return new SecurityUser(user.get().getUserid(), user.get().getPasswd(), grantedAuthorities);
    }
    
}
