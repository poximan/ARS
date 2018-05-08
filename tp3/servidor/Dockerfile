FROM img-sshd:latest

# usuario hdonato
# --------------------------
RUN adduser hdonato --disabled-password
USER hdonato
WORKDIR /home/hdonato

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

# usuario labella
# --------------------------
RUN adduser labella --disabled-password
USER labella
WORKDIR /home/labella

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

# usuario emallon
# --------------------------
RUN adduser emallon --disabled-password
USER emallon
WORKDIR /home/emallon

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

# usuario atorrico
# --------------------------
RUN adduser atorrico --disabled-password
USER atorrico
WORKDIR /home/atorrico

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

# usuario jlima
# --------------------------
RUN adduser jlima --disabled-password
USER jlima
WORKDIR /home/jlima

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

# usuario jdevia
# --------------------------
RUN adduser jdevia --disabled-password
USER jdevia
WORKDIR /home/jdevia

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

CMD ["/usr/sbin/sshd", "-D"]
# /usr/sbin/sshd -D
