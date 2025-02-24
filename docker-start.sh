sudo docker stop esup-siscol 
sudo docker rm  esup-siscol
sudo docker run -d -p 8980:8080  --name=esup-siscol  --mount  type=bind,source="$(pwd)"/etc/esup-siscol,target=/etc/esup-siscol local/esup-siscol:v2.0.0-SNAPSHOT --dns={dns_server} 
##sudo docker run -d -p 8080:8080  --name=apogee local/apogee:v0.1 
