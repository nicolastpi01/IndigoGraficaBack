package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.controllers.services.EMailService;

import javax.mail.MessagingException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MailSenderTest {

    @Autowired
    EMailService eMailService;

    //@Test
    public void sendTest(){
        try {
            eMailService.send();
            Assertions.assertTrue(true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
