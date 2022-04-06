package com.hamonize.portal;

import com.hamonize.portal.social.CustomOAuth2UserService;
import com.hamonize.portal.user.SecurityUserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${attach.path}")
    private String path;
    
    @Autowired
    LoginSuccessHandler authSuccessHandler;

    @Autowired
    Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Autowired
    LoginFailureHandler authFailureHandler;

    @Autowired
    SecurityUserDetailsService userDetailService;
    
    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/img/**")
            .antMatchers("/images/**")
            .antMatchers("/vendors/**")
            .antMatchers("/board/**/**/**")
            .antMatchers("/signup/**")
            .antMatchers("/argon/**");

            
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**", "/signup/**", "/login/**","/","/setsession/**","/oauth2/**","/product/**").permitAll()
            .anyRequest().authenticated();
        
        http.formLogin()
            .loginPage("/login")
            .usernameParameter("userid")
            .passwordParameter("passwd")
            .loginProcessingUrl("/login/login")
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler);
        
        
        http.oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)

            .and()
                .successHandler(oauth2LoginSuccessHandler)
                .failureHandler(authFailureHandler);    
        
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
            .logoutSuccessHandler(logoutSuccessHandler)
            .invalidateHttpSession(false);
        
        }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    

}
