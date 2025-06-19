sudo docker stop esup-siscol 
sudo docker rm  esup-siscol
sudo docker run -d -p 8080:8080  --name=esup-siscol  --mount  type=bind,source="$(pwd)"/etc/esup-siscol,target=/etc/esup-siscol local/esup-siscol:v2.0.2-RC  
##sudo docker run -d -p 8080:8080  --name=apogee local/apogee:v0.1 
