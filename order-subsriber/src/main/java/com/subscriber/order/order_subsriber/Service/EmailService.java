package com.subscriber.order.order_subsriber.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.subscriber.order.order_subsriber.Bean.EmailConn;
import com.subscriber.order.order_subsriber.Model.MailModel;

public class EmailService {
    
    @Autowired
    private EmailConn mailConn = new EmailConn();

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void SendOrderNotification(MailModel mail){
        logger.info("Send Email Notification serice Start");
        
    }

}
