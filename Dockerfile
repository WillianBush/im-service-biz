FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zhongxin@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /web-domain
RUN mkdir -p /web-domain/upload
RUN mkdir -p /web-domain/logs

WORKDIR /web-domain

EXPOSE 9000

ADD web-domain.jar ./


CMD java -Xms2048m -Xmx6144m -Djava.security.egd=file:/dev/./urandom -jar web-domain.jar


