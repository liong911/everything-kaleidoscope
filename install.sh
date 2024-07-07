
#!/bin/bash

echo "#####################################################################"
echo "1.打包vue"

cd ./kaleidoscope-ui
npm install
npm run build

# 删除原静态资源目录
rm -rf ../src/main/resources/static/
# 拷贝新文件
cp -r ./dist ../src/main/resources/static/

echo "#####################################################################"
echo "2.打包服务"

cd ..
mvn -Pprod -Dmaven.test.skip=true clean package

echo "#####################################################################"
echo "3.生成release"

if [ -d "./release" ]; then
    echo "删除原release"
    rm -rf ./release
fi
mkdir -p release

# 拷贝jar
cp ./target/everything-kaleido.jar ./release
# 拷贝模板
cp -r ./templates ./release
# 拷贝启动脚本
cp ./src/main/resources/start.sh ./release

echo "#####################################################################"
echo "打包完成"
