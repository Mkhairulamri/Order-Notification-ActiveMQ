package com.example.order.order.Bean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class getConnection {

    @Value("${spring.activemq.broker-name}")
    private static String BROKER_NAME;

    @Value("${spring.artemis.embedded.queues}")
    private static String QUEUE_NAME;

    public static boolean checkConnection() {
        try {
            MBeanServerConnection mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName queueObjectName = new ObjectName("org.apache.activemq:brokerName=" + BROKER_NAME +
                    ",destinationType=Queue,destinationName=" + QUEUE_NAME);

            return mBeanServer.isRegistered(queueObjectName);
        } catch (Exception e) {
            System.err.println("Error checking queue via JMX: " + e.getMessage());
            return false;
        }
    }
}
