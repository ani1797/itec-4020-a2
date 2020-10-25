FROM maven AS build_container
MAINTAINER Anirudh Aggarwal <anirudh.aggarwal@mail.utoronto.ca>
WORKDIR /apps/${project.artifactId}
COPY src ./src/
COPY ["pom.xml", "./"]
RUN mvn clean package

FROM tomcat as deploy_container
WORKDIR "$CATALINA_HOME/webapps"
COPY --from=build_container "/apps/${project.artifactId}/target/${project.artifactId}.war" ./ROOT.war
EXPOSE 8080