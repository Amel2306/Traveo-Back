# Utiliser une image OpenJDK
FROM openjdk:17-jdk-slim

# Copier l'application compilée dans le conteneur
COPY build/libs/reservation-0.0.1-SNAPSHOT.jar app.jar

# Définir le point d'entrée
ENTRYPOINT ["java", "-jar", "/app.jar"]