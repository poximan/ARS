docker network create red-docker
docker run --name exec-sshd-cli --net=red-docker -ti img-sshd-cli bash
