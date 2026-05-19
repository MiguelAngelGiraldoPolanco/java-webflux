# --- ETAPA 1: Compilación y descarga de dependencias ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiamos el archivo de configuración de dependencias
COPY pom.xml .

# Truco experto: Descargamos las dependencias de Maven dentro de Docker 
# para que se queden guardadas en la caché de Docker y no en tu Mac
RUN mvn dependency:go-offline -B

# Copiamos el código fuente de la aplicación
COPY src ./src

# Compilamos el proyecto omitiendo los tests por ahora
RUN mvn clean package -DskipTests

# --- ETAPA 2: Contenedor final de ejecución ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto por defecto de Spring Boot
EXPOSE 8080

# Comando para arrancar nuestra app reactiva
ENTRYPOINT ["java", "-jar", "app.jar"]