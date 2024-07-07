#!/bin/bash

echo "#####################################################################"
echo "启动企业万花筒"

java -jar everything-kaleido.jar \
  --MYSQL_URL=localhost:3306 --MYSQL_ACCOUNT=root --MYSQL_PASSWORD=123456 
