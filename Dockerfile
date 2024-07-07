FROM azul/zulu-openjdk-centos:8u412-jdk

WORKDIR /opt/app
COPY ./release /opt/app/

CMD java -Xms256m -Xmx256m \
    -server -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=128m \
    -XX:+UseG1GC -XX:G1HeapRegionSize=8m -XX:G1ReservePercent=25 -XX:InitiatingHeapOccupancyPercent=30 -XX:SoftRefLRUPolicyMSPerMB=0 \
    -Djava.security.egd=file:/dev/./urandom \
    ${JAVA_OPTIONS} -jar /opt/app/everything-kaleido.jar
