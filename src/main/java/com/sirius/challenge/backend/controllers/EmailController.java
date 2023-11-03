package com.sirius.challenge.backend.controllers;

import com.sirius.challenge.backend.documentation.IEmailController;
import com.sirius.challenge.backend.security.models.User;
import com.sirius.challenge.backend.security.repositories.UserRepository;
import com.sirius.challenge.backend.services.impl.BrevoEmailService;
import com.sirius.challenge.backend.services.impl.MailgunEmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api")
public class EmailController implements IEmailController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BrevoEmailService brevoEmailService;

    @Autowired
    private MailgunEmailService mailgunEmailService;

    @PostMapping("/users/current/email")
    public ResponseEntity<Object> sendEmail(
            @RequestParam String toEmail, @RequestParam String subject,@RequestParam String body) throws IOException {

        boolean isEmailSent = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getEmailDailyCount() > 999){
            return new ResponseEntity<>("Daily limit reached", HttpStatus.FORBIDDEN);
        }
        try {
            brevoEmailService.sendPlainEmail(user.getEmail(), toEmail, subject, body);
            user.mailSent();
            userRepository.save(user);
            isEmailSent = true;
        } catch (IOException ex){
            System.out.println("Email error" + ex);
        }
        if (isEmailSent){
            try {
                mailgunEmailService.sendPlainEmail(user.getEmail(), toEmail, subject, body);
                user.mailSent();
                userRepository.save(user);
            } catch (IOException ex){
                System.out.println("Email error" + ex);
            }
        }
        return new ResponseEntity<>("Email was sent", HttpStatus.OK);
    }
}
