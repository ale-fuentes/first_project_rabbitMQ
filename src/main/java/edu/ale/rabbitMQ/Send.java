package edu.ale.rabbitMQ;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

@Profile("send")
public class Send {

	static Logger logger = LoggerFactory.getLogger(Send.class);
	private static String QUEUE_NAME = "msgale";

	public static void main(String ...args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME,false,false,false, null);
		String message ="Hi Neo ...";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
		logger.info("[!] Send message for queue");
		channel.close();
		connection.close();
	}
}
