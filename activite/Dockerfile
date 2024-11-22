# Utiliser une image OpenJDK
FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y curl


# Copier l'application compilée dans le conteneur
COPY build/libs/activite-0.0.1-SNAPSHOT.jar app.jar

# Définir le point d'entrée
ENTRYPOINT ["java", "-jar", "/app.jar"]