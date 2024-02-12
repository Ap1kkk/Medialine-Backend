FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

# Используем другой образ OpenJDK для запуска приложения
FROM openjdk:17-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл из предыдущего этапа
COPY --from=build /app/target/*.jar /app/app.jar

# Открываем порт 8080
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/app.jar"]