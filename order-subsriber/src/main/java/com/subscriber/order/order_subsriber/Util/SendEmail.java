package com.subscriber.order.order_subsriber.Util;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.subscriber.order.order_subsriber.Model.CustomerModel;
import com.subscriber.order.order_subsriber.Model.MailData;

public class SendEmail {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    @Value("${email.mail_from}")
    String mailFrom;

    public void sendEmailWithoutAttach(String message, Session session, String subject, MailData email) {
        try {
            MimeMessage msgProcess = new MimeMessage(session);
            CustomerModel cust = email.getCustomerModel();
            msgProcess.setHeader("Content-type", "text/HTML; charset=UTF-8");
            msgProcess.setHeader("format", "flowed");
            msgProcess.setHeader("Content-Transfer-Encoding", "8bit");
            msgProcess.setFrom(new InternetAddress(mailFrom, "NoReply-JD"));
            msgProcess.setReplyTo(InternetAddress.parse(mailFrom, false));
            msgProcess.setSubject(subject);
            msgProcess.setText(message, "UTF8");
            msgProcess.setSentDate(new Date());
            msgProcess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cust.getCustMail(), false));
            Transport.send(msgProcess);
            logger.info("Success Send Mail");
        } catch (Exception e) {
            logger.error("Error Send Mail Exception Process " + e.getMessage(), e);
        }
    }

}
