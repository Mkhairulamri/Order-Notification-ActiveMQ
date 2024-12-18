package com.example.order.order.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PubliserOrder {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Value("${spring.artemis.embedded.queues}")
    public String QueueDestination;

    public void pushOrder(Map<String, Object> mapParam) throws JmsException, JsonProcessingException {
        jmsTemplate.convertAndSend(QueueDestination, mapper.writeValueAsString(mapParam));
    }

}
