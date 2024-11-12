#!/bin/bash
docker rm $(docker ps -a -q)
docker-compose down -v
docker build --no-chache -t consumer -f Dockerfile_consumer .
docker build --no-cache -t producer -f Dockerfile_producer .
docker build --no-cache -t mysql -f Dockerfile_mysql .
docker-compose -p 'proj' up --force-recreate