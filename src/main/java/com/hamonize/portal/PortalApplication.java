package com.hamonize.portal;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableRedisHttpSession
@RestController
@SpringBootApplication
public class PortalApplication {
    @Autowired
    HttpSession httpSession;

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}


    @GetMapping(path = "/setsession")
    public String setsessionAPI() {
        String value = "ttesttt";
        
        httpSession.setAttribute("KEY", value);
        String returnValue = LocalDateTime.now().toString() + " \n<br>session set id : " + httpSession.getId() + " \n<br>session set Value : " + value;
        return returnValue;
    }
}
