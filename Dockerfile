FROM openjdk:8-jre-alpine
MAINTAINER xuxueli

ENV PARAMS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD mybatis-plus.jar /app.jar

ENTRYPOINT ["sh","-c","java -jar /app.jar $PARAMS"]