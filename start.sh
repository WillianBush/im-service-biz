name=im-service-biz
docker stop $name
docker rm $name
docker build -t $name:uat .
docker run -d -p 9001:9001 -e "SPRING_PROFILES_ACTIVE=uat" -v /root/docker/im-service-biz/logs:/im-service-biz/logs:rw --name $name $name:uat
