FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-pedido-0.0.1-SNAPSHOT.jar hr-pedido.jar
ENTRYPOINT ["java","-jar","/hr-pedido.jar"]