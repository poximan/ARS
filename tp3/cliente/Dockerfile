FROM img-sshd:latest

# usuario hdonato
# --------------------------
RUN adduser hdonato --disabled-password
USER hdonato
WORKDIR /home/hdonato
RUN mkdir .ssh
WORKDIR /home/hdonato/.ssh
# ssh-keygen -b 2048 -t rsa -f /home/hdonato/.ssh/id_rsa -q -N ""
# scp ~/.ssh/id_rsa.pub hdonato@exec-sshd-serv:/home/hdonato/.ssh/uploaded_key.pub
