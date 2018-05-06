FROM img-sshd:latest

# usuario labella
# --------------------------
RUN adduser labella --disabled-password
USER labella
WORKDIR /home/labella

RUN mkdir .ssh
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
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root

CMD ["/usr/sbin/sshd", "-D"]
