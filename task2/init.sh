#!/bin/bash
docker rm $(docker ps -a -q)
docker-compose down -v
docker build -t consumer -f Dockerfile_consumer .
docker build -t producer -f Dockerfile_producer .
docker build -t mysql -f Dockerfile_mysql .
docker-compose -p 'proj' up --force-recreate