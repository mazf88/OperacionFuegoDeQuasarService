FROM openjdk:8-alpine
EXPOSE 8081
VOLUME /tmp
COPY ./target/OperacionFuegoDeQuasarService-0.0.1.jar OperacionFuegoDeQuasarService-0.0.1.jar
RUN apk update
# Fire up our Spring Boot app by default
CMD ["sh","-c","java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /OperacionFuegoDeQuasarService-0.0.1.jar","--spring.profiles.active=h2"]
#ENTRYPOINT ["java","-jar","OperacionFuegoDeQuasarService-0.0.1.jar","--spring.profiles.active=h2"]