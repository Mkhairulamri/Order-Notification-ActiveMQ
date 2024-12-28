package com.subscriber.order.order_subsriber.Bean;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class EmailConn {

    @Autowired
    private Environment env;

    private static final Logger logger = LoggerFactory.getLogger(EmailConn.class);

    public Session getEmailSession(String emailTo){
        logger.info("SSL Email Connection Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", env.getProperty("env.mail_host"));
        props.put("mail.smtp.socketFactory.port",env.getProperty("env.mail_ssl_port"));
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", env.getProperty("env.smtp_auth"));
        props.put("mail.smtp.port", env.getProperty("env.smtp_port"));

        Authenticator auth = new Authenticator() {
            /**
             * @return
             */
            @SuppressWarnings("unused")
            protected PasswordAuthentication getAuthentication(){
                return new PasswordAuthentication(env.getProperty("env.mail_from"), env.getProperty("env.mail_password"));
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        logger.info("Session created");
        return session;
        
    }

}
