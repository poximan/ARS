FROM ubuntu:16.04

RUN apt-get update

RUN apt-get install -y iputils-ping
RUN apt-get install -y openssh-server

RUN mkdir /var/run/sshd

EXPOSE 22
