package com.example.demo;

import com.example.services.EMailCosoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.MessagingException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MailSenderTest {

    @Autowired
    EMailCosoService eMailCosoService;

    //@Test
    public void sendTest(){
        try {
            eMailCosoService.send();
            Assertions.assertTrue(true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
