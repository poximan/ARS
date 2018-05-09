docker network create red-docker
docker run --entrypoint /bin/bash -i -t --net=red-docker --name exec-sshd-cli img-sshd-cli
