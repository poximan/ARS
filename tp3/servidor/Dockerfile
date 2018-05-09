FROM img-sshd:latest

# alta usuario hdonato
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

USER root
# fin usuario hdonato
# --------------------------

# alta usuario labella
# --------------------------
RUN useradd -ms /bin/bash labella
WORKDIR /home/labella
RUN echo 'labella:labella' | chpasswd

USER labella

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root
# fin usuario labella
# --------------------------

# alta usuario emallon
# --------------------------
RUN useradd -ms /bin/bash emallon
WORKDIR /home/emallon
RUN echo 'emallon:emallon' | chpasswd

USER emallon

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root
# fin usuario emallon
# --------------------------

# alta usuario atorrico
# --------------------------
RUN useradd -ms /bin/bash atorrico
WORKDIR /home/atorrico
RUN echo 'atorrico:atorrico' | chpasswd

USER atorrico

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root
# fin usuario atorrico
# --------------------------

# alta usuario jlima
# --------------------------
RUN useradd -ms /bin/bash jlima
WORKDIR /home/jlima
RUN echo 'jlima:jlima' | chpasswd

USER jlima

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root
# fin usuario jlima
# --------------------------

# alta usuario jdevia
# --------------------------
RUN useradd -ms /bin/bash jdevia
WORKDIR /home/jdevia
RUN echo 'jdevia:jdevia' | chpasswd

USER jdevia

RUN mkdir .ssh
# usr(rwx)  grp(rwx) oth(rwx)
RUN chmod 700 .ssh
RUN touch .ssh/authorized_keys
RUN chmod 600 .ssh/authorized_keys

USER root
# fin usuario jdevia
# --------------------------

# fin usuarios
# --------------------------
WORKDIR /home
# service ssh start
# cat hdonato_id_rsa.pub >> ./authorized_keys
