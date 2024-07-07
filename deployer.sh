#!/bin/bash

sh ./install.sh

echo "#####################################################################"
echo "制作镜像"

projectName=${1}
docker build -t $projectName .

echo "#####################################################################"
echo "推送镜像"
docker tag $projectName:latest ccr.ccs.tencentyun.com/everything/$projectName:latest
docker push ccr.ccs.tencentyun.com/everything/$projectName:latest
