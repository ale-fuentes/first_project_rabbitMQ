package edu.ale.teste.rabbitMQ;

import com.rabbitmq.client.*;
import edu.ale.entity.MessageBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Profile("receive")
public class Receive {

	static Logger logger = LoggerFactory.getLogger(Receive.class);
	private final static String QUEUE_NAME = "msgale";

	public static void main(String ...args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME,false,false,false, null);
		logger.info("[!] Waiting for messages. Please for exit press 'CTRL + C'");
		Consumer consumer = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
				String message = new String(body, "UTF-8");
				MessageBroker messageBroker = new MessageBroker();
				messageBroker.setMessage(message);

				logger.info("[!] Message are been recieved. Content: '" + message + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
