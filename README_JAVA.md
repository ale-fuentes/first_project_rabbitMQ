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

### Configurate Dependencies for Sprint Boot

In te site [mvn repository](https://mvnrepository.com/artifact/mysql/mysql-connector-java) seed how to add in our file configuration of project, 'pom.xml' the mysql dependencie:

```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
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
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <version>2.5.2</version>
</dependency>
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
