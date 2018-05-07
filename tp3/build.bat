@echo off
docker stop exec-sshd-serv
docker rm exec-sshd-serv
docker rmi img-sshd-serv

docker stop exec-sshd-cli
docker rm exec-sshd-cli
docker rmi img-sshd-cli

docker container prune
docker image prune
docker network prune

docker build -t img-sshd .
docker build -t img-sshd-serv ./servidor
docker build -t img-sshd-cli ./cliente

docker network create red-docker
start cmd.exe -k docker run --name exec-sshd-serv --net=red-docker -ti img-sshd-serv bash
start cmd.exe -k docker run --name exec-sshd-cli --net=red-docker -ti img-sshd-cli bash
