FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER zhongxin@gmail.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /springboot-mybatis
RUN mkdir -p /springboot-mybatis/upload
RUN mkdir -p /springboot-mybatis/logs

WORKDIR /springboot-mybatis

EXPOSE 8998

ADD web-domain.jar ./


CMD java -Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom -jar web-domain.jar


