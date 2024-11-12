#!/bin/bash

docker rm $(docker ps -a -q)
docker build --no-cache -t task1 .

#docker tag task1 heyko12/task1
#docker login
#docker push heyko12/task1

docker run --rm -v task1_volume:/ISD_3rd_LAB_BIT task1 /bin/bash /ISD_3rd_LAB_BIT/task1/run_from_docker.sh