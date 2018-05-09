@echo off
docker stop exec-sshd-serv
docker rm exec-sshd-serv
docker rmi img-sshd-serv

docker container prune
docker image prune
docker network prune

docker build -t img-sshd .
docker build -t img-sshd-serv ./servidor
