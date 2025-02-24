imageTag=2.0.0-SNAPSHOT
echo "Building CAS docker image tagged as [$imageTag]"

docker build  --tag="local/esup-siscol:v$imageTag" . \
  && echo "Built CAS image successfully tagged as local/esup-siscol:v$imageTag" \
  && docker images "local/esup-siscol:v$imageTag"
