package com.hamonize.portal;

import com.hamonize.portal.mail.IMailSender;
import com.hamonize.portal.mail.MailSender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${attach.path}")
	private String path;

    // private final PortalUrlInterceptor portalUrlInterceptor;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
        .addResourceLocations("file:"+path+"/");
    }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(portalUrlInterceptor)
    //             .addPathPatterns("/**")
    //             .excludePathPatterns();
    // }

    @Bean
    public IMailSender mailsender(){
        return new MailSender();
    }

}
