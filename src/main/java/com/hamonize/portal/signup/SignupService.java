package com.hamonize.portal.signup;

import java.util.List;

import javax.transaction.Transactional;

import com.hamonize.portal.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SignupService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SignupRepository sr;
    
    public User save(User vo) {	
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPasswd(passwordEncoder.encode(vo.getPasswd()));
        vo.setRole("ROLE_USER");
        return sr.save(vo);
	}
    
}
