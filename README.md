# ServerPrototype
Server prototype

To test the application:

1. Install RabbitMQ server
2. Enable RabbitMQ plugins using command 'rabbitmq-plugins enable plugin_name'
    - rabbitmq_management
    - rabbitmq_management_agent
    - rabbitmq_web_mqtt
    - rabbitmq_mqtt
    - rabbitmq_web_dispatch
3. Start RabbitMQ server using command 'rabbitmq-server'
4. Start ServerPrototypeApplication using command 'java -jar build/libs/server-prototype-0.1.0.jar'
5. Open javascript client in browser. Client is located in src/main/resources/client.html
