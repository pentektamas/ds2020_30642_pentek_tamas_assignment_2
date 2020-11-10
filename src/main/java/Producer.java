import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {

    private final static String QUEUE_NAME = "ActivityQueue";
//    private final static String EXCHANGE_NAME = "ActivityExchange";
//    private final static String ROUTING_KEY = "Activity";

    public static void sendData(List<String> data) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Hello World!";

            /*
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("x-delayed-type", "direct");
            channel.exchangeDeclare(EXCHANGE_NAME, "x-delayed-message", true, false, args);

            AMQP.BasicProperties.Builder props = new AMQP.BasicProperties.Builder();
            Map<String, Object> headers = new HashMap<String, Object>();
            headers.put("x-delay", 3000);
            props.headers(headers);
            */

            int i = 0;
            for (String element : data) {
                //channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(), element.getBytes(StandardCharsets.UTF_8));
                channel.basicPublish("", QUEUE_NAME, null, element.getBytes(StandardCharsets.UTF_8));
                System.out.println("[" + i + "]" + " Sent ");
                i++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
/*
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(), message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[" + 1 + "]" + " Sent ");
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, props.build(), message.getBytes(StandardCharsets.UTF_8));
            System.out.println("[" + 2 + "]" + " Sent ");
 */
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("Timeout Exception");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception");
        }
    }
}
