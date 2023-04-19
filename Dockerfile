# ## Build Angular
# FROM node:19 as angular

# WORKDIR /app

# # Copy files
# COPY ./clientNg/angular.json .
# COPY ./clientNg/package.json .
# COPY ./clientNg/package-lock.json .
# COPY ./clientNg/tsconfig.app.json .
# COPY ./clientNg/tsconfig.json .
# COPY ./clientNg/tsconfig.spec.json .
# COPY ./clientNg/src ./src
# #
# # Install Angular
# RUN npm install -g @angular/cli

# # Install packages and build
# RUN npm i
# RUN ng build

## Build Spring Boot
FROM maven:3.9.0-eclipse-temurin-19 AS springboot

WORKDIR /app

## build
COPY ./mvnw .
COPY ./mvnw.cmd .
COPY ./pom.xml .
COPY ./src ./src
# COPY ./clientNg/dist/client-ng ./src/main/resources/static

# Copy compiled angular app to static directory
# COPY --from=angular /app/dist/client-ng ./src/main/resources/static

RUN mvn package -Dmaven.test.skip=true

# Copy the final Jar file
FROM eclipse-temurin:19-jre

WORKDIR /app

COPY --from=springboot /app/target/FinalProj-0.0.1-SNAPSHOT.jar server.jar

## run
ENV PORT=8080
##ENV DB_SERVER=localhost
##ENV DB_PORT=3306
##ENV SPRING_DATASOURCE_USERNAME=root
##ENV SPRING_DATASOURCE_PASSWORD=rootroot
ENV SPRING_DATASOURCE_URL=jdbc:mysql://root:QtUPPV3rwZoBNvKt3I0p@containers-us-west-89.railway.app:7030/finalProj

EXPOSE ${PORT}

ENTRYPOINT java -Dserver.port=${PORT} -jar server.jar