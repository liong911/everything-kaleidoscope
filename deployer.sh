#!/bin/bash

sh ./install.sh

echo "#####################################################################"
echo "制作镜像"

projectName=${1}
docker build -t $projectName:1.0 .

echo "#####################################################################"
echo "推送镜像"
docker tag everything-kaleido:latest ccr.ccs.tencentyun.com/everything/$projectName:latest
docker push ccr.ccs.tencentyun.com/everything/$projectName:latest
