FROM maven:3-jdk-8 AS builder

LABEL MAINTAINER="4ra1n"

COPY ./ /usr/src/
COPY ./settings.xml /root/.m2/settings.xml

WORKDIR /usr/src

RUN cd /usr/src; \
        mvn -U clean package -Dmaven.test.skip=true

FROM openjdk:8-jre

LABEL MAINTAINER="4ra1n"

COPY --from=builder /usr/src/fake-mysql-cli-0.0.4.jar /cli.jar

EXPOSE 3306

CMD ["java","-jar","/cli.jar","-p","3306"]
