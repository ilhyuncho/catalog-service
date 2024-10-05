FROM eclipse-temurin:17 AS builder
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} catalog-service.jar
#계층 jar모드를 적용해 아카이브에서 계층을 추출 함
RUN java -Djarmode=layertools -jar catalog-service.jar extract

FROM eclipse-temurin:17
RUN useradd spring
USER spring
WORKDIR workspace
 # 첫번째 단계에서 추출한 jar 계층을 두 번째 단계로 복사
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
# 스프링 부트 런처를 사용해 우버 jar 대신 계층으로 애플리케이션을 시작
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
# error fix Spring Boot 3.2.X: JarLauncher Path
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

