# ------------------------------
# Etapa 1: Build (compilación)
# ------------------------------

# Usa una imagen base con JDK 24 para poder compilar el código de JAVA
# "AS build" le da un bombre a esta etapa para poder reutilizar sus archivos después
FROM eclipse-temurin:24-jdk AS build

# Definir el directorio de trabajo dentro del contenedor
# Todas las operaciones siguientes se ejecutarán dentro de /app
WORKDIR /app

# Copiar el Maven Wrapper (script que permite ejecutar el Maven sin instalarlo)
COPY mvnw ./

# Copiar el wrapper cmd
COPY .mvnw.cmd ./

# Copiar la carpeta .mvn que contiene la configuración del Maven Wrapper
COPY .mvn .mvn

# Copiar el archivo de configuración del proyecto Maven
COPY pom.xml ./

# Copiar el código fuente del proyecto
COPY src ./src

# Dar permisos de ejecución al script mvnw
# En sistemas Linux los scripts deben tener permisos de ejecución
RUN chmod +x mvnw

# Ejecutar Maven para compilar el proyecto
# clean: limpia builds anteriores
# package: compila y generar el archivo .jar
RUN ./nvnw clean package -DskipTests

# ------------------------------
# Etapa 2: Runtime (ejecución)
# ------------------------------

# Usar una imagen para ejecutar la aplicación en base a la compilación de la etapa anterior
# No necesitas JDK porque la aplicación ya está compilada
FROM eclipse-temurin:24-jre

# Definir nuevamente un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo .jar generado en la etapa del build
# --from=build indica que se copie desde la etapa llamada "build"
# /app/target/*.jar es donde Maven genera el jar compilado
# Se puede renombrar mediante esta forma: /app/target/*.jar app.jar
COPY --from=build /app/target/*.jar app.jar

# Este es el puerto por defecto de aplicaciones Spring Boot
EXPOSE 8080

# Define el comando que se ejecutará al iniciar el contenedor
# Inicia la app de Spring ejecutando el archivo jar
ENTRYPOINT ["java", "-jar", "app.jar"]


