package com.bridgelabz.addressBookApp.controller;

import com.bridgelabz.addressBookApp.dto.AuthUserDTO;
import com.bridgelabz.addressBookApp.dto.MailDTO;
import com.bridgelabz.addressBookApp.service.AuthenticationService;
import com.bridgelabz.addressBookApp.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bridgelabz.addressBookApp.dto.LoginDTO;

@RestController

public class AuthenticationController {

    EmailService emailService;
    AuthenticationService authenticationService;

    public AuthenticationController(EmailService emailService, AuthenticationService authenticationService) {
        this.emailService = emailService;
        this.authenticationService = authenticationService;
    }

    //============================UC9(Register and Login for a User)
    @PostMapping(path = "/register")
    public String register(@RequestBody AuthUserDTO user){
        return authenticationService.register(user);
    }

    @PostMapping(path ="/login")
    public String login(@RequestBody LoginDTO user){
        return authenticationService.login(user);
    }

    //==============================Sendmail======================//
    @PostMapping(path="/sendMail")
    public String sendMail(@RequestBody MailDTO user){ emailService.sendEmail(user.getTo(), user.getSubject(), user.getBody());
        return "Mail Sent";
    }

    // 🔹 Forgot Password (User provides email & new phone)
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email, @RequestBody PasswordResetDTO passwordResetDTO) {
        return ResponseEntity.ok(authenticationService.forgotPassword(email, passwordResetDTO.getNewPhone()));
    }

    // 🔹 Reset Password (User provides email, current phone & new phone)
    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<String> resetPassword(@PathVariable String email, @RequestBody PasswordResetDTO passwordResetDTO) {
        return ResponseEntity.ok(authenticationService.resetPassword(email, passwordResetDTO.getCurrentPhone(), passwordResetDTO.getNewPhone()));
    }


}