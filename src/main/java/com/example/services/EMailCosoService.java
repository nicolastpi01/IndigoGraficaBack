package com.example.services;

import com.example.model.Pedido;
import com.example.util.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;

@Service
public class EMailCosoService {


    @Qualifier("getJavaMailSender")
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    MailContentBuilder mailContentBuilder;

    public void send() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("d.caminos54@gmail.com");
        helper.setSubject("Email Test");
        helper.setText("",true);


        emailSender.send(message);
    }

    public void send(Pedido pedido) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(pedido.getPropietario().getEmail());
        helper.setSubject("Solicitud de pedido" + pedido.getNombre());
        String body = mailContentBuilder.build(pedido);
        helper.setText(body,true); //TODO html here!
        addAttachment(helper,pedido);
        emailSender.send(message);
    }

    private void addAttachment(MimeMessageHelper helper, Pedido pedido) {
        pedido.getFiles().forEach( file -> {
            DataSource dataSource = new ByteArrayDataSource(file.getData(), "application/png");
            try {
                helper.addAttachment("imagen_item_" + file.getName() + ".png", dataSource);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
