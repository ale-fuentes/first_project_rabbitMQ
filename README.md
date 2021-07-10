# First Step with RabbitMQ
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd)

Hi guys, welcome this simple project about [Message Broker](https://en.wikipedia.org/wiki/Message_broker), but ussing [RabbitMQ](https://www.rabbitmq.com/).

## My technologies & tools

* ![](https://img.shields.io/badge/OS-Windows-informational?style=flat&logo=windows&logoColor=white&color=cdcdcd)
* ![](https://img.shields.io/badge/Editor-Eclipse-informational?style=flat&logo=eclipse-ide&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/Repository-Docker-informational?style=flat&logo=Docker&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/Project_Management-Apache_Maven-informational?style=flat&logo=ApacheMaven&logoColor=white&color=cdcdcd)
* ![](https://img.shields.io/badge/Code-Java-informational?style=flat&logo=java&logoColor=white&color=cdcdcd)
![](https://img.shields.io/badge/Code-SpringBoot-informational?style=flat&logo=springboot&logoColor=white&color=cdcdcd)
* ![](https://img.shields.io/badge/Message_Broker-RabbitMQ-informational?style=flat&logo=RabbitMQ&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/Project_Management-MySql-informational?style=flat&logo=MySql&logoColor=white&color=cdcdcd)


## RabbitMQ :: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.rabbitmq/amqp-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.rabbitmq/amqp-client)
 
First time, I get a [image docker of RabbitMQ](https://hub.docker.com/_/rabbitmq)
next command:

```
$ docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```
next, I show it im my browser

![image](https://user-images.githubusercontent.com/67701790/125144256-aa135300-e0f3-11eb-822b-99876bd1223c.png)

The RAbbitMQ running 

![image](https://user-images.githubusercontent.com/67701790/125144393-2017ba00-e0f4-11eb-83fe-edd2b1853083.png)

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

### Get image docker of MySQL

Get imagen of [Docker MySql]https://hub.docker.com/_/mysql)...

```
$ docker pull mysql
...
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest
```
Now up the mySql

```
$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
```

test if MySql are running

```
$ docker ps
CONTAINER ID   IMAGE                   COMMAND                  CREATED              STATUS              PORTS
                                                                                           NAMES
991e767c2815   mysql:latest            "docker-entrypoint.s…"   About a minute ago   Up About a minute   3306/tcp, 33060/tcp
                                                                                           some-mysql
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


