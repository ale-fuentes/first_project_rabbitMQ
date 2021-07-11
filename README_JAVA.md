# Steps with Project Java
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/Code-Java-informational?style=flat&logo=java&logoColor=white&color=cdcdcd)

- [Return Main Session](README.md)

## Create project

You can get this project for your testing or stating a new project.
Next steps are explanation how I make this project.

### Apache Maven
With [Apache Maven](http://maven.apache.org/index.html) we can management our projects, add, update or delete dependencies the our projects. Is one of the way ... but its use is eassy.

See in [mavem apache site](https://maven.apache.org/guides/getting-started/) how to started a project, I get this next command line for creting a project:

```
$ mvn archetype:generate -DgroupId=edu.ale -DartifactId=first-step-rabbitMQ -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
After finish the process, the legend BUILD SUCCESS show:

![image](https://user-images.githubusercontent.com/67701790/125146382-e64ab180-e0fb-11eb-96d4-86dde44156c8.png)

For add new dependencies in our project, I find they in [search maven site](https://mvnrepository.com/artifact/com.rabbitmq/amqp-client/5.12.0).
I search the RabbitMQ Java client, [oficial site rabbitmq](https://www.rabbitmq.com/tutorials/tutorial-one-java.html) say the its is also in the central Maven repository, with the groupId com.rabbitmq and the artifactId amqp-client.

#### Configurate Dependencies for Sprint Boot

In te site [mvn repository](https://mvnrepository.com/artifact/mysql/mysql-connector-java) seed how to add in our file configuration of project, 'pom.xml' the mysql dependencie:

```
<!-- MySQL -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>${mysql.version}</version>
    <scope>runtime</scope>
</dependency>
```

Next, add in configuration, how to the project connect with MySql. This configutation are write in 'application.properties' file (where has '--new-paswword--' you write the same password the changed when install mysql from docker):

```
# connection mysql
spring.datasource.url=jdbc:mysql://localhost:3306/messages
spring.datasource.username=root
spring.datasource.password='--new-password--'
```

This project are think with Spring Boot, therefore we need add next dependency in our file 'pom.xml' (we can search it in [mvn repository](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc/2.5.2)):

```
<!-- SPRING BOOT -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <version>${spring-boot.version}</version>
</dependency>
```

The versions are wrote in the section 'properties':

```
<!-- MySQL -->
<mysql.version>8.0.25</mysql.version>

<!-- SPRING BOOT -->
<spring-boot.version>2.5.2</spring-boot.version>
```

### Write coding in Java for first testing

In project java, create a class 'MySQLConnectioonApplication', in there we write one test for know if connection with docker image are been success:

```
package edu.ale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class MySQLConnectionApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MySQLConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO messagebroker (message) VALUES (?)";

        int result = jdbcTemplate.update(sql, "mensage de teste");

        if(result > 0){
            System.out.println("A new row has been create with sucess!");
        }
    }
}
```

### Verify if row are saved in the table MySql

Connect in our mysql cli from docker, and verify if the table '_messagebroker_' are change:

```
# mysql -uroot -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 18
Server version: 8.0.25 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
mysql> select * from messagebroker;
+----+------------------+
| id | message          |
+----+------------------+
|  1 | mensage de teste |
+----+------------------+
1 row in set (0.00 sec)
```

### Frist concept about RabbitMQ with Java

In the site [RabbitMQ API guide](https://www.rabbitmq.com/api-guide.html), talk about some methods that can usse for create conections:

- **Channel**: _represents an AMQP 0-9-1 channel, and provides most of the operations (protocol methods)._
- **Connection**: _represents an AMQP 0-9-1 connection_
- **ConnectionFactory**: _constructs Connection instances_
- **Consumer**: _represents a message consumer_
- **DefaultConsumer**: _commonly used base class for consumers_
- **BasicProperties**: _message properties (metadata)_
- **BasicProperties.Builder**: _builder for BasicProperties_

#### Write our example Send and Receiver ussing RabbitMQ

First example, is for sending one message, this project are make in package 'rabbitMQ':

```
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
```

![Example Sending message](https://share.vidyard.com/watch/YYpteq2w6fUkrvuBQY9zBs?)
