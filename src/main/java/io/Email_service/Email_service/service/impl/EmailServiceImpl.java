package io.Email_service.Email_service.service.impl;

import io.Email_service.Email_service.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Objects;

import static io.Email_service.Email_service.utils.EmailUtils.getEmailMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String UTF_8_ENCODING = "UTF-8";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender emailSender;
    @Override
    @Async
    public void sendSimpleMailMessage(String name, String to, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(getEmailMessage(name,host,token));
            emailSender.send(message);

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    @Async
    public void sendMyMessageWithAttachment(String name, String to, String token) {

        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMessage(name,host,token));
            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/store.jpg"));
            FileSystemResource forts = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/1677547747692-R1.pdf"));
            FileSystemResource fot = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/imageStoreRoom.jpg"));
            helper.addAttachment(Objects.requireNonNull(fort.getFilename()), fort);
            helper.addAttachment(Objects.requireNonNull(forts.getFilename()), forts);
            helper.addAttachment(Objects.requireNonNull(fot.getFilename()), fot);
            emailSender.send(message);

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    @Override
    @Async
    public void sendMyMessageWithFiles(String name, String to, String token) {
        try{
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMessage(name,host,token));
            FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/store.jpg"));
            FileSystemResource forts = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/1677547747692-R1.pdf"));
            FileSystemResource fot = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/imageStoreRoom.jpg"));
            helper.addInline(Objects.requireNonNull(getContentId(fort.getFilename())), fort);
            helper.addInline(Objects.requireNonNull(getContentId(forts.getFilename())), forts);
            helper.addInline(Objects.requireNonNull(getContentId(fot.getFilename())), fot);
            emailSender.send(message);

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }


    @Override
    @Async
    public void sendHtmlEmail(String name, String to, String token) {
        try{
            Context context = new Context();
            context.setVariable("name", name);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true,UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(getEmailMessage(name,host,token));
            emailSender.send(message);

        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    @Async
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {

    }
    private String getContentId(String fileName){
        return "<" + fileName + ">";
    }

}
