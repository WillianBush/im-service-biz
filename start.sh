name=springboot-mybatis
docker stop $name
docker rm $name
docker build -t $name:prd .
docker run -d -p 8998:8998 -e "SPRING_PROFILES_ACTIVE=prd" -v /root/docker/springboot-mybatis/logs:/springboot-mybatis/logs:rw -v /root/docker/springboot-mybatis/upload:/springboot-mybatis/upload:rw --name $name $name:prd
