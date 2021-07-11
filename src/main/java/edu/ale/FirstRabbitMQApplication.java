package edu.ale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstRabbitMQApplication {

    private final static String QUEUE_NAME = "hiale";
    static Logger logger
            = LoggerFactory.getLogger(FirstRabbitMQApplication.class);
}
