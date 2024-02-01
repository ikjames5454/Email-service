package io.Email_service.Email_service.service;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String token);
    void sendMyMessageWithAttachment(String name, String to, String token);
    void sendMyMessageWithFiles(String name, String to, String token);
    void sendHtmlEmail(String name, String to, String token);
    void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token);
}
