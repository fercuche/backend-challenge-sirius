package com.sirius.challenge.backend.services;

import java.io.IOException;

public interface IEmailService {

    void sendPlainEmail(String from, String to, String subject, String body) throws IOException;

    //Email create(Email email) throws EmailException;
}
