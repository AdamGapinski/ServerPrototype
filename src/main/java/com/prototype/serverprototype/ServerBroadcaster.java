package com.prototype.serverprototype;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerBroadcaster implements CommandLineRunner {

    private RabbitTemplate rabbitTemplate;

    public ServerBroadcaster(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(() -> {
            long messageCounter = 0;

            String message;
            while (true) {
                message = "Message from server number " + messageCounter;
                System.out.println("Sending: " + message);
                rabbitTemplate.convertAndSend("amq.topic", "broadcast", message);
                messageCounter++;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
