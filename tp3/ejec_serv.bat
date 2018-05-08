docker network create red-docker
docker run --name exec-sshd-serv --net=red-docker -ti img-sshd-serv bash
