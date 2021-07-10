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

