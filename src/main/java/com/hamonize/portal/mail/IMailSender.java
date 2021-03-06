package com.hamonize.portal.mail;

import org.springframework.mail.javamail.JavaMailSender;

public interface IMailSender extends JavaMailSender{
    
    public String getHost();
    public String getProtocol();
    public String getUsername();
    public String getPassword();
    public int getPort();

}
