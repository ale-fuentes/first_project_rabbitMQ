# Steps with MySQL
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd)

- [Return Main Session](README.md)

## Get image docker of MySQL

Get imagen of [Docker MySql](https://hub.docker.com/_/mysql)...

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

## Testing 

Next, from CMD os outher bash, open client SQL 

```
$ docker exec -it some-mysql bash
root@991e767c2815:/# mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.25 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```

how to good practice, I do change my password defoult for other (where has '_--new-password--_' you write your personal password):

```
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY '--new-password--';
Query OK, 0 rows affected (0.01 sec)

mysql>
```

after change this datas, you can exit console ussing 'quit' and 'exit' commands:

```
mysql> quit
Bye
root@991e767c2815:/# exit
exit
```
After, I can create our custom configuration for MySQL. Ussing command **cat > my-custom.cnf** in the bash for create file. when finish write the configuration, push key 'CTRL + D' and finishing created the file:

```
root@991e767c2815:/etc/mysql/conf.d# ls 
docker.cnf  mysql.cnf
root@991e767c2815:/etc/mysql/conf.d# cat > my-custom.cnf
[mysqld]
max_connections=250

root@991e767c2815:/etc/mysql/conf.d# ls
docker.cnf  my-custom.cnf  mysql.cnf

root@991e767c2815:/etc/mysql/conf.d# cat my-custom.cnf 
[mysqld]
max_connections=250

root@991e767c2815:/etc/mysql/conf.d#
```

## Create Data Base for our Project
Now, I make to database for our project ussing command client mysql:

#### Fisrt step, we create database

```
$ docker exec -it some-mysql bash
root@991e767c2815:/# mysql -uroot -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.25 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
mysql> CREATE DATABASE messages;
Query OK, 1 row affected (0.01 sec)

mysql> show DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| messages           |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.00 sec)

mysql>
```

#### Second step, we create some tables

Before, we can select what DB we ussing, for that ussing command 'USE'

```
mysql> USE messages;
Database changed
```

We need one table, where RabbitMQ when read a queue, and has a new iten, the iten are save in this table. This table has name 'messagebroker'.

| field | detail | description|
| --- | --- | --- |
| id | long, primary key |field key of table |
| menssage | varchar 100 characters, not null | field where the message are save |

```
mysql> CREATE TABLE messagebroker(
    -> id INT NOT NULL,
    -> message VARCHAR(100) NOT NULL,
    -> PRIMARY KEY(id));
Query OK, 0 rows affected (0.04 sec)
```

For know is table are been create, ussing command 'DESCRIBE':

```
mysql> DESCRIBE messagebroker
    -> ;
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| id      | int          | NO   | PRI | NULL    |       |
| message | varchar(100) | NO   |     | NULL    |       |
+---------+--------------+------+-----+---------+-------+
2 rows in set (0.00 sec)

mysql>
```

## Some command Docker

To start your container run:

```
sudo docker start [container_name]
```

Stop the container, use the command:

```
sudo docker stop [container_name]
```

To restart the container run:

```
sudo docker restart [container_name]
```

Delete the Container. Before deleting a container, make sure you stop it first. Then, remove the docker container with:

```
sudo docker rm [container_name]
```
