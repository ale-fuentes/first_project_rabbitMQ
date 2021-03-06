# Steps with MySQL
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/OS-Windows-informational?style=flat&logo=windows&logoColor=white&color=cdcdcd)

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
$ docker run --name some-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=messages mysql
```

test if MySql are running

```
$ docker ps
CONTAINER ID   IMAGE                   COMMAND                  CREATED              STATUS              PORTS
                                                                                           NAMES
991e767c2815   mysql:latest            "docker-entrypoint.s…"   About a minute ago   Up About a minute   3306/tcp, 33060/tcp
                                                                                           some-mysql
```

For verify what IP are use, we verify with next command:

```
$ docker inspect test-mysql
```

some information that command get for we see:

```
...
"Ports": {
              "3306/tcp": [
                  {
                      "HostIp": "0.0.0.0",
                      "HostPort": "3306"
                  },
                  {
                      "HostIp": "::",
                      "HostPort": "3306"
                  }
              ],
              "33060/tcp": null
          },
...
...
   "IPAddress": "172.17.0.3",
...
...
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
    -> id INT NOT NULL AUTO_INCREMENT,
    -> message VARCHAR(100) NOT NULL,
    -> PRIMARY KEY(id));
Query OK, 0 rows affected (0.03 sec)
```

For know is table are been create, ussing command 'DESCRIBE':

```
mysql> DESCRIBE messagebroker;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int          | NO   | PRI | NULL    | auto_increment |
| message | varchar(100) | NO   |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
2 rows in set (0.00 sec)

mysql>
```

Create table *notification*:

```
mysql> CREATE TABLE notification(
    -> id INT NOT NULL AUTO_INCREMENT,
    -> message_id INT NOT NULL,
    -> message VARCHAR(100) NOT NULL,
    -> server_ip VARCHAR(40) NOT NULL,
    -> date DATE,
    -> PRIMARY KEY (id)
    -> );
Query OK, 0 rows affected (0.09 sec)

mysql> DESCRIBE notification;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| message_id | int          | NO   |     | NULL    |                |
| message    | varchar(100) | NO   |     | NULL    |                |
| server_ip  | varchar(40)  | NO   |     | NULL    |                |
| date       | date         | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
5 rows in set (0.01 sec)

mysql>
```
