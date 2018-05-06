@echo off
docker build -t img-sshd .
docker build -t img-sshd-serv ./servidor
docker build -t img-sshd-cli ./cliente
