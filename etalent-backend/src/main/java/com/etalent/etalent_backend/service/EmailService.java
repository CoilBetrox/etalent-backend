package com.etalent.etalent_backend.service;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    //@Value("${}")
    //private String fromEmail;

    private static String fromEmailV1;
}
