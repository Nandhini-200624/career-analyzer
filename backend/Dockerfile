FROM eclipse-temurin:21-jdk

RUN apt-get update && \
    apt-get install -y tesseract-ocr && \
    apt-get clean

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java","-Xmx512m","-jar","target/career-analyzer-0.0.1-SNAPSHOT.jar"]