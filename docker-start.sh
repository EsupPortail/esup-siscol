sudo docker stop apogee
sudo docker rm apogee
sudo docker run -d -p 8980:8080  --name=apogee  --mount  type=bind,source="$(pwd)"/etc/apogee/conf,target=/etc/apogee/conf local/apogee:v0.1  --dns=193.50.151.50 
##sudo docker run -d -p 8080:8080  --name=apogee local/apogee:v0.1 
