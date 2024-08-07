package com.etalent.etalent_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    private String fromEmail;

    public EmailService(JavaMailSender mailSender, @Value("${spring.mail.username}") String fromEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
    }

    public void sendVerificationEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Verifica tu cuenta de eTalent");
        message.setText("Por favor, haz clic en el siguiente enlace para verificar tu cuenta: "
                + "http://localhost:8080/verify-email?token=" + token);
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Restablece tu contraseña de eTalent");
        message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace: "
                + "http://localhost:8080/reset-password?token=" + token);
        mailSender.send(message);
    }
}