#!/bin/sh -

set -o errexit

echo "doing maven build"
./mvnw clean package
echo "maven build done"

echo "building docker"
docker build . -t localhost:32000/movies-spring-web:0.0.1
echo "docker builded"

echo "publishing docker"
docker push localhost:32000/movies-spring-web
echo "docker published"
