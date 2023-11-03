package com.sirius.challenge.backend.services.impl;

import com.sirius.challenge.backend.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrevoEmailService implements IEmailService {

    @Autowired
    private ApiClient defaultClient;

    @Override
    public void sendPlainEmail(String from, String toEmail, String subject, String body) throws IOException {

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();
            api.setApiClient(defaultClient);
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail(from);
            sender.setName(from);
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(toEmail);
            toList.add(to);
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTextContent(body);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject(subject);
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println(response.toString());
        } catch (ApiException e) {
            System.out.println("Exception occurred:- " + e.getMessage());
        }
    }
}