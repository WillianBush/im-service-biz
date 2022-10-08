FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zhongxin@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /im-service-biz
RUN mkdir -p /im-service-biz/logs

WORKDIR /im-service-biz

EXPOSE 9001

ADD im-service-biz.jar ./


CMD java -Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom -jar im-service-biz.jar


