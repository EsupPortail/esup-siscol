#!/bin/bash

docker stop esup-siscol > /dev/null 2>&1
docker rm esup-siscol > /dev/null 2>&1
image_tag=0.1.14
docker run -d -p 8080:8080  --name="esup-siscol" local/esup-siscol:v0.1.14 --dns=193.50.151.50
#docker logs -f esup-sisco
