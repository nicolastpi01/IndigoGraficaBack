package com.example;

import com.example.exception.PedidoConComentariosNoCerradosException;
import com.example.services.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MailController {

    @Autowired
    protected EMailService eMailService;

    @GetMapping("/mail")
    public ResponseEntity<Object> send(){
        try {
            eMailService.send();
        } catch (MessagingException e) {
            Logger.getLogger("Falló el envio de mail").log(Level.SEVERE, e.toString()); ;
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PostMapping("/mail/presupuesto/{id}")
    public ResponseEntity<Object> send(@PathVariable Long id){
        try {
            eMailService.send(id);
        } catch (PedidoConComentariosNoCerradosException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todavía quedan comentarios por revisar");
        } catch (MessagingException e) {
            Logger.getLogger("Falló el envio de mail").log(Level.SEVERE, e.toString());
        } catch (Exception e){
            Logger.getLogger("se rompio pero mas fuerte").log(Level.SEVERE, e.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
