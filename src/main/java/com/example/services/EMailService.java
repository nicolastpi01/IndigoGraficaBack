package com.example.services;

import com.example.model.FileDB;
import com.example.model.Pedido;
import com.example.util.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EMailService {


    @Qualifier("getJavaMailSender")
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public PedidoStorageService pedidoStorageService;

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

    public void send(Long idPedido) throws MessagingException, Exception {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("d.caminos54@gmail.com");
        helper.setSubject("Presupuesto de pedido listo");
        Pedido pedido = pedidoStorageService.findPedido(idPedido).orElseThrow(() -> new Exception("pedido no encontrado"));
        String body = mailContentBuilder.build(pedido);
        helper.setText(body,true); //TODO html here!
        addAttachment(helper,pedido);
        emailSender.send(message);
    }

    private void addAttachment(MimeMessageHelper helper, Pedido pedido) {
        if(!pedido.getPresupuesto().isEmpty()){
            FileDB fileDB = pedido.getPresupuesto().get(0).getFile();
            DataSource dataSource = new ByteArrayDataSource(fileDB.getData(), fileDB.getType());
            try {
                helper.addAttachment("Presupuesto pedido nro " + pedido.getId() + fileDB.getName() , dataSource);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
