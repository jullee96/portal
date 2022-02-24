package com.hamonize.portal;

import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import com.hamonize.portal.user.SecurityUser;
import com.hamonize.portal.user.SecurityUserDetailsService;
import com.hamonize.portal.util.SHA256Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SecurityUserDetailsService userDetailsService;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        logger.info("### CustomAuthenticationProvider authenticate ### ");

		String userid = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

        logger.info("input userid >> ", userid);
        logger.info("input password >> ", password);

        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(userid);
            
        if (user == null ){
            throw new UsernameNotFoundException(userid); 
        
        } else{
            SHA256Util sha256 = new SHA256Util(user.getSalt());
        
            logger.info("조회한 userid  >> ", user.getUserid());
            logger.info("조회한 userpw  >> ", user.getPasswd());

            if( !sha256.matches(password, user.getPasswd())){
                throw new BadCredentialsException(userid); 
            }

            return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

        }
        

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
      }
    
}