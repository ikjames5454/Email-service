package io.Email_service.Email_service.service.impl;

import io.Email_service.Email_service.model.Confirmation;
import io.Email_service.Email_service.model.User;
import io.Email_service.Email_service.repository.ConfirmationRepo;
import io.Email_service.Email_service.repository.UserRepo;
import io.Email_service.Email_service.service.EmailService;
import io.Email_service.Email_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ConfirmationRepo confirmationRepository;
    @Autowired
    private EmailService emailService;
    @Override
    public User saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

//        emailService.sendSimpleMailMessage(user.getName(), user.getEmail(),confirmation.getToken());
//        emailService.sendMyMessageWithAttachment(user.getName(), user.getEmail(),confirmation.getToken());
        emailService.sendMyMessageWithFiles(user.getName(), user.getEmail(),confirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        return Boolean.TRUE;
    }
}




