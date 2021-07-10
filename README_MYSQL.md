# Steps with MySQL
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd)

- [Return Main Session](README.md)

## Get image docker of MySQL

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
