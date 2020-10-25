#!/usr/bin/env bash

# Argument Variables
APP_TAG="servlet-application:1.0.0";
DOCKER_USERNAME="ani1797";

DOCKER_BUILD_ARGS=""

# Check for required Tools
if [ ! $(command -v mvn) ]; then
    echo "Maven Installation not found! Please install and retry!" && exit 1
fi

if [ ! $(command -v docker) ]; then
    echo "Docker installation not found! Please install and retry!" && exit 1
fi

# Generate the docker build file
mvn resources:copy-resources@generate-deploy-artifact

# Build a docker container with the built application
docker build $DOCKER_BUILD_ARGS -t "$APP_TAG" .

# Login to docker
read -rp 'Docker Username: ' DOCKER_USERNAME
read -srp 'Docker Password: ' DOCKER_PASSWORD
docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"

# Push the build image
docker tag $APP_TAG "$DOCKER_USERNAME/$APP_TAG"
docker push "$DOCKER_USERNAME/$APP_TAG"

# Cleanup the generated docker build file
rm -rf ./Dockerfile