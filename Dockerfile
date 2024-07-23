# Primera fase: Construcción de la aplicación
# Usa una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Construye la aplicación y empaqueta en un archivo JAR
RUN mvn clean package -DskipTests

# Segunda fase: Ejecución de la aplicación
# Usa una imagen base de OpenJDK 18 para ejecutar la aplicación
FROM openjdk:17-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR construido desde la fase de construcción
COPY --from=build /app/target/bidder-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que corre tu aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
