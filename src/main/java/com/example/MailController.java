package com.example;

import com.example.services.EMailCosoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MailController {

    @Autowired
    protected EMailCosoService eMailCosoService;

    @PostMapping("/mail")
    public ResponseEntity<Object> send(){
        try {
            eMailCosoService.send();
        } catch (MessagingException e) {
            Logger.getLogger("Falló el envio de mail").log(Level.SEVERE, e.toString()); ;
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
}
