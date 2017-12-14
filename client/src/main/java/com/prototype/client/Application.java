package com.prototype.client;


import com.prototype.serverprototype.api.Client;
import com.prototype.serverprototype.api.ClientImpl;
import com.prototype.serverprototype.api.MessageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author Adam Gapiński
 */
public class Application {
    public static void main(String[] args) throws IOException, TimeoutException {
        List<Client> clients = createClients(args);
        sendToServer(clients);
    }

    private static void sendToServer(List<Client> clients) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String message = scanner.nextLine();

            if (message.equals("exit")) {
                break;
            }

            clients.forEach(client -> client.sendMessage(message));
        }
    }

    private static List<Client> createClients(String[] args) throws IOException, TimeoutException {
        int connections = 1;

        try {
            if (args.length > 0) {
                connections = Integer.valueOf(args[0]);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < connections; ++i) {
            Client client = new ClientImpl();

            MessageHandler messageHandler = new MessageHandlerImpl();
            client.registerMessageHandler(messageHandler);

            clients.add(client);
        }

        return clients;
    }
}

class MessageHandlerImpl implements MessageHandler {
    @Override
    public void onMessage(String message) {
        System.out.printf("Received '%s'\n", message);
    }
}

