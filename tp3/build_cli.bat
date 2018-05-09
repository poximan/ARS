@echo off
docker stop exec-sshd-cli
docker rm exec-sshd-cli
docker rmi img-sshd-cli

docker container prune
docker image prune
docker network prune

docker build -t img-sshd .
docker build -t img-sshd-cli ./cliente
