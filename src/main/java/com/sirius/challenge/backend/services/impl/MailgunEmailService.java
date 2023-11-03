package com.sirius.challenge.backend.services.impl;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.sirius.challenge.backend.services.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailgunEmailService implements IEmailService {

    @Value("${mailgun.api.key}")
    private String apikey;

    @Value("${mailgun.domain}")
    private String domain;

    @Override
    public void sendPlainEmail(String from, String to, String subject, String body) throws IOException {
        Message message = Message.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .text(body)
                .build();
        try {
            sendMail(message);
            System.out.println("Mail sent");
        } catch (IOException ex) {
            throw new IOException("Email not sent");
        }
    }

    public void sendMail(Message message) throws IOException {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(apikey)
                .createApi(MailgunMessagesApi.class);
        mailgunMessagesApi.sendMessage(domain, message);
    }
}
