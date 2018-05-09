#!/bin/sh
docker build -t img-sshd .
docker build -t img-sshd-serv ./servidor
docker build -t img-sshd-cli ./cliente

docker network create red-docker
docker run --name exec-sshd-serv --net=red-docker -ti img-sshd-serv bash
docker run --name exec-sshd-cli --net=red-docker -ti img-sshd-cli bash
