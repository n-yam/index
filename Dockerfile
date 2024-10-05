FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /opt

COPY ./src ./src

RUN javac src/main/java/index/Main.java -cp src/main/java -d build/classes \
 && jar -cvfe build/index.jar index.Main -C build/classes . -C src/main/resources . \
 && jlink --add-modules java.base --no-header-files --no-man-pages --output ./jre-21


FROM alpine:3.20.3

WORKDIR /usr/local/index

COPY --from=build /opt/jre-21 /opt/jre-21
COPY --from=build /opt/build/index.jar ./index.jar

ENV JAVA_HOME=/opt/jre-21

CMD ["/opt/jre-21/bin/java", "-jar", "./index.jar"]