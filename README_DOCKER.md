# Steps with Docker
![](https://img.shields.io/badge/by-Alejandro.Fuentes-informational?style=flat&logoColor=white&color=cdcdcd) ![](https://img.shields.io/badge/OS-Windows-informational?style=flat&logo=windows&logoColor=white&color=cdcdcd)

- [Return Main Session](README.md)

# Docker :: ![](https://img.shields.io/badge/Repository-Docker-informational?style=flat&logo=Docker&logoColor=white&color=cdcdcd)

Doker are a popular gestor of repositories.<br />
[Docker hub](https://hub.docker.com/) are easy is a service provided by Docker for finding and sharing container images with your team.

## Some command Docker

To start your container run:

```
$ docker start [container_name]
```

Stop the container, use the command:

```
$ docker stop [container_name]
```

To restart the container run:

```
$ docker restart [container_name]
```

Delete the Container. Before deleting a container, make sure you stop it first. Then, remove the docker container with:

```
$ docker rm [container_name]
```
Run bash some docker image:

```
$ docker exec -it some-mysql bash
```
