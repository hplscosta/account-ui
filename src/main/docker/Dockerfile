FROM anapsix/alpine-java:jre8
MAINTAINER hugo.costa.tek@gmail.com

ENV HOME /var/lib/account-ui
ENV JARFILE /var/lib/account-ui/app.jar

VOLUME /var/lib/account-ui

COPY account-ui.jar /var/lib/account-ui/app.jar

RUN chmod 500 /var/lib/account-ui/app.jar

COPY docker-entrypoint.sh /entrypoint.sh

RUN chmod 500 /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]