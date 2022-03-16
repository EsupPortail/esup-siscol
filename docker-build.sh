imageTag=0.1
echo "Building CAS docker image tagged as [$imageTag]"

docker build  --tag="local/apogee:v$imageTag" . \
  && echo "Built CAS image successfully tagged as local/apogee:v$imageTag" \
  && docker images "local/apogee:v$imageTag"
