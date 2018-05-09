FROM img-sshd:latest

# usuario hdonato
# --------------------------
RUN useradd -ms /bin/bash hdonato
WORKDIR /home/hdonato
RUN echo 'hdonato:hdonato' | chpasswd

USER hdonato

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

RUN echo "generando clave asimetrica"
RUN ssh-keygen -t rsa -f .ssh/id_rsa -q -N ""

# scp .ssh/id_rsa.pub hdonato@exec-sshd-serv:/home/hdonato/.ssh/hdonato_id_rsa.pub
