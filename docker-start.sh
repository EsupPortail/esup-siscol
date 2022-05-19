sudo docker stop esup-siscol 
sudo docker rm  esup-siscol
sudo docker run -d -p 8980:8080  --name=esup-siscol  --mount  type=bind,source="$(pwd)"/etc/esup-siscol,target=/etc/esup-siscol local/esup-siscol:v0.1.14  --dns=193.50.151.50 
##sudo docker run -d -p 8080:8080  --name=apogee local/apogee:v0.1 
