FROM eclipse-temurin:21-jdk

RUN apt-get update && \
    apt-get install -y \
    tesseract-ocr \
    tesseract-ocr-eng

ENV TESSDATA_PREFIX=/usr/share/tesseract-ocr/5/tessdata/

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java","-jar","target/career-analyzer-0.0.1-SNAPSHOT.jar"]