FROM gradle:8.5-jdk17

WORKDIR /app

COPY build.gradle settings.gradle gradlew gradlew.bat /app/
COPY gradle /app/gradle

RUN gradle dependencies --no-daemon

COPY . /app

CMD ["gradle", "clean", "test"]
