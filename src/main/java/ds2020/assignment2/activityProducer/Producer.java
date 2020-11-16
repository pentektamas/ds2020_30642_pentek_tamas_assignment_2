package ds2020.assignment2.activityProducer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final static String QUEUE_NAME = "ActivityQueue";
    private final static String HOST = "squid.rmq.cloudamqp.com";
    private final static String V_HOST = "uzlkddss";
    private final static String USERNAME = "uzlkddss";
    private final static String PASSWORD = "5LK8YhJlftB2UzjZ-P2nQupLfFmOBWLo";

    public static void sendData(List<String> data) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setVirtualHost(V_HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            int i = 0;
            for (String element : data) {
                channel.basicPublish("", QUEUE_NAME, null, element.getBytes(StandardCharsets.UTF_8));
                System.out.println("[" + i + "]: " + element);
                i++;
                Thread.sleep(1000);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("Timeout Exception");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Thread exception");
        }
    }
}
