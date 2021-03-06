package com.hamonize.portal.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository ur;

    @Override
    @Transactional
    public SecurityUser loadUserByUsername(String userid) throws UsernameNotFoundException {
        logger.info("loadUserByUsername : userid >> {}",userid);

        Optional<User> user = ur.findByUserid(userid);
        
        logger.info("\n\n\nuser role??? {}", user.get().getRole());     

        if(!user.isPresent()){
            return null;
        } else if(user.get().getRole().equals("ROLE_GUEST")){
           throw new DisabledException("이메일 미인증");
        } else if(user.get().getStatus().equals("IA")){
            throw new AccountExpiredException("회원 탈퇴한 계정");
        }
        
        return new SecurityUser(user.get());

    }
    
    public boolean isPresentId(String userid){
        Optional<User> user = ur.findByUserid(userid);
           
        return user.isPresent(); 
    }
}
